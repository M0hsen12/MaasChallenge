package com.test.masschallenge.ui.bottomSheet

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.test.masschallenge.R
import com.test.masschallenge.databinding.FragmentBottomSheetDetailBinding
import com.test.masschallenge.di.modules.BindModule
import com.test.masschallenge.di.viewModelInjections.InjectionViewModelProvider
import com.test.masschallenge.model.response.detail.Details
import com.test.masschallenge.model.response.places.Images
import com.test.masschallenge.ui.adapter.ImagesAdapter
import com.test.masschallenge.ui.base.BaseBottomSheetFragment
import com.test.masschallenge.util.createUnderLineOnText
import com.test.masschallenge.util.materialSimpleProgressDialog
import com.test.masschallenge.viewModel.bottomSheet.FragmentBottomSheetDetailViewModel
import javax.inject.Inject


@BindModule
class FragmentBottomSheetDetail :
    BaseBottomSheetFragment<FragmentBottomSheetDetailBinding, FragmentBottomSheetDetailViewModel>() {

    @Inject
    lateinit var mViewModelFactoryActivity: InjectionViewModelProvider<FragmentBottomSheetDetailViewModel>

    override fun getLayoutId() = R.layout.fragment_bottom_sheet_detail
    lateinit var progressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        progressDialog = materialSimpleProgressDialog(requireContext())

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            Handler().post {
                val bottomSheet =
                    (dialog as? BottomSheetDialog)?.findViewById<View>(R.id.design_bottom_sheet) as? FrameLayout
                bottomSheet?.let {
                    BottomSheetBehavior.from(it).state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        getData()
        observeData()

    }

    private fun observeData() {
        viewModel?.detailLiveData?.observe(viewLifecycleOwner) {
            handleComingDataForUI(it)
        }
    }

    private fun handleComingDataForUI(details: Details) {
        handleTexts(details)
        setUpImagesRv(details.description.images.orEmpty())

    }

    private fun handleTexts(details: Details) {
        progressDialog.dismiss()

        binding.detail = details

        binding.detailViewsGroup.visibility = View.VISIBLE

        binding.fullAddress =
            "${details.location.address.locality} ${details.location.address.neighbourhood} ${details.location.address.streetAddress}"

        binding.fullPostalCode = " postal code : ${details.location.address.postalCode}"

        binding.detailLink.apply {
            createUnderLineOnText(this, getString(R.string.more_info))
            visibility = View.VISIBLE
            setOnClickListener {
                sendUserToBrowser(details.infoUrl)
            }
        }

    }

    private fun sendUserToBrowser(url: String) {

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)

    }

    private fun setUpImagesRv(images: List<Images>) {
        images.takeIf { it.isNotEmpty() }.apply {

            binding.detailImagesRv.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                val mAdapter = ImagesAdapter()
                adapter = mAdapter
                mAdapter.submitList(images)

            }
        } ?: run {
            binding.detailImagesPlaceHolder.visibility = View.VISIBLE

        }
    }

    private fun getData() {
        arguments?.getString(EXTRA_INPUT)?.let {
            getDetailForCurrentLocation(it)
        }

    }

    private fun initUI() {
        viewModel = mViewModelFactoryActivity.get(this, FragmentBottomSheetDetailViewModel::class)
        progressDialog.show()
    }

    private fun getDetailForCurrentLocation(id: String) {
        viewModel?.getLocationDetail(id)
    }


    companion object {
        const val EXTRA_INPUT = "_EXTRA.INPUT"
        fun newInstance(
            id: String?,
        ): FragmentBottomSheetDetail {
            return FragmentBottomSheetDetail().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_INPUT, id)

                }
            }
        }

    }
}
