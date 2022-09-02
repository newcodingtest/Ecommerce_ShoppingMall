package com.yoon.shopping.service;

import com.yoon.shopping.dto.ItemFormDto;
import com.yoon.shopping.entity.Item;
import com.yoon.shopping.entity.ItemImg;
import com.yoon.shopping.repository.ItemImgRepository;
import com.yoon.shopping.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;
    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile)throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";
        
        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(itemImgLocation, oriImgName,
                    itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }

        //상품 이미지 정보 저장
        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);
    }

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile)
            throws Exception{
            //기존의 등록된 상품 정보 조회
            if(!itemImgFile.isEmpty()){
                ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
                        .orElseThrow(EntityNotFoundException::new);

                //기존 이미지 파일 삭제
                if(!StringUtils.isEmpty(savedItemImg.getImgName())){
                    fileService.deleteFile(itemImgLocation+"/"+savedItemImg.getImgName());
                }

                String oriImgName = itemImgFile.getOriginalFilename();
                String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
                String imgUrl = "/images/item/" + imgName;
                savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
            }

    }

}
