package com.test.masschallenge.ui.bottomSheet

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.test.masschallenge.R
import com.test.masschallenge.databinding.FragmentBottomSheetDetailBinding
import com.test.masschallenge.di.modules.BindModule
import com.test.masschallenge.di.viewModelInjections.InjectionViewModelProvider
import com.test.masschallenge.ui.base.BaseBottomSheetFragment
import com.test.masschallenge.viewModel.bottomSheet.FragmentBottomSheetDetailViewModel
import javax.inject.Inject

@BindModule
class FragmentBottomSheetDetail :
    BaseBottomSheetFragment<FragmentBottomSheetDetailBinding, FragmentBottomSheetDetailViewModel>() {

    @Inject
    lateinit var mViewModelFactoryActivity: InjectionViewModelProvider<FragmentBottomSheetDetailViewModel>

    override fun getLayoutId() = R.layout.fragment_bottom_sheet_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
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
        viewModel = mViewModelFactoryActivity.get(this, FragmentBottomSheetDetailViewModel::class)
        Log.e("TAG", "onViewCreated: sheet" )
        arguments?.getString(EXTRA_INPUT)?.let {
            Log.e("TAG", "onViewCreated: $id" )
        }


    }


    override fun onResume() {
        super.onResume()
    }


    companion object {
        const val EXTRA_INPUT = "_EXTRA.INPUT"
        fun newInstance(
            id: String?,
//            hint: String,
//            title: String? = null,
//            inputType: Int = InputType.TYPE_CLASS_TEXT,
//            onSubmitClick: ((dialog: Dialog, progressHandler: ProgressHandler, input: String) -> Unit)?
        ): FragmentBottomSheetDetail {
            return FragmentBottomSheetDetail().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_INPUT, id)

                }
            }
        }

    }
}
