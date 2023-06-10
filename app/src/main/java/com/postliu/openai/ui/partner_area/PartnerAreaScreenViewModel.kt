package com.postliu.openai.ui.partner_area

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.postliu.openai.domain.usecase.IGetPartnerAreaGoodsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PartnerAreaScreenViewModel @Inject constructor(
    private val getPartnerAreaGoodsUseCase: IGetPartnerAreaGoodsUseCase
) : ViewModel() {

    fun partnerAreaGoods(goodsType: Int) = getPartnerAreaGoodsUseCase(goodsType).cachedIn(viewModelScope)
}
