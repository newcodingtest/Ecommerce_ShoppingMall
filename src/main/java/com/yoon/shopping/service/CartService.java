package com.yoon.shopping.service;

import com.yoon.shopping.dto.CartDetailDto;
import com.yoon.shopping.dto.CartItemDto;
import com.yoon.shopping.entity.Cart;
import com.yoon.shopping.entity.CartItem;
import com.yoon.shopping.entity.Item;
import com.yoon.shopping.entity.Member;
import com.yoon.shopping.repository.CartItemRepository;
import com.yoon.shopping.repository.CartRepository;
import com.yoon.shopping.repository.ItemRepository;
import com.yoon.shopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;


    /**
     * 로그인 사용자의 장바구니 등록
     * */
    public Long addCart(CartItemDto cartItemDto, String email){
        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository
                            .findByEmail(email);

        Cart cart = cartRepository
                        .findByMemberId(member.getId());
        if(cart == null){
            cart = Cart.createCar(member);
            cartRepository.save(cart);
        }

        CartItem savedCartItem = cartItemRepository
                                    .findByCartIdAndItemId(cart.getId(), item.getId());

        if(savedCartItem != null){
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else {
            CartItem cartItem =
                    CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }

    /**
     * 로그인 사용자의 장바구니 리스트 가져오기
     * */
    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String email){
        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

        Member member = memberRepository
                .findByEmail(email);
        Cart cart = cartRepository
                .findByMemberId(member.getId());
        if(cart == null){
            return cartDetailDtoList;
        }

        cartDetailDtoList = cartItemRepository
                .findCartDetailDtoList(cart.getId());

        return cartDetailDtoList;
    }

    /**
     * 상품 주문자와 로그인 사용자의 검증
     * */
    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String email){
        Member curMember = memberRepository
                .findByEmail(email);
        CartItem cartItem = cartItemRepository
                .findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        Member savedMember = cartItem
                .getCart()
                .getMember();

        if(!StringUtils.equals(curMember.getEmail(),
                savedMember.getEmail())){
            return false;
        }
        return true;
    }

    /**
     * 장바구니 상품 수량 수정
     * */
    public void updateCartItemCount(Long cartItemId, int count){
        CartItem cartItem = cartItemRepository
                .findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        cartItem.updateCount(count);
    }

    /**
     * 장바구니 상품 삭제
     * * */
    public void deleteCartItem(Long cartItemId){
        CartItem cartItem = cartItemRepository
                .findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }


}
