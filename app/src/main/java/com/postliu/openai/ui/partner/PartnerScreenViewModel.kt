package com.postliu.openai.ui.partner

import androidx.lifecycle.ViewModel
import com.postliu.openai.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PartnerScreenViewModel @Inject constructor() : ViewModel() {

    val defaultList = listOf(PartnerAction.Company, PartnerAction.Business)

}

sealed class PartnerAction(
    val leadingIcon: Int,
    val trailingIcon: Int,
    val title: String,
    val id: Int,
) {
    object Company :
        PartnerAction(R.drawable.partner_company_icon, R.drawable.partner_company_arrow_right, "企业合伙人", 6)

    object Business :
        PartnerAction(R.drawable.partner_business_icon, R.drawable.partner_business_arrow_right, "商家合伙人", 5)
}
