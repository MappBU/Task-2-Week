package com.example.topappbarwithdriwerlayiutmenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.topappbarwithdriwerlayiutmenu.databinding.DetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// Простой фрагмент. Наследуемся от BottomSheetDialogFragment
// Интерфейс для реализации кнопки закрытия Щита
class Details : BottomSheetDialogFragment(), View.OnClickListener {

    private var binding: DetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.details, container, false)

        // Принимаем Переданные из Фрагмента Карточки данные во фрагмент Щита
        binding?.titleMovieDetails?.text = arguments?.getString("tittleMovie").toString()
        binding?.longMovieDetails?.text = arguments?.getString("longMovie").toString()
        binding?.actorsMovieDetails?.text = arguments?.getString("actorsMovie").toString()

        // Если во фрагменте содержится титул фильм такой-то, то подгружаем нужную картинку
        when(binding?.titleMovieDetails?.text){
            getString(R.string.bladerunner) -> binding?.imageMovieDetails?.setImageResource(R.drawable.bladerunner)
            getString(R.string.mrnobody) -> binding?.imageMovieDetails?.setImageResource(R.drawable.mrnobody)
            getString(R.string.riddick) -> binding?.imageMovieDetails?.setImageResource(R.drawable.riddick)
            getString(R.string.goandwatch) -> binding?.imageMovieDetails?.setImageResource(R.drawable.goandwatch)
        }

        // Кнопка закрытия
        binding?.exitIcon?.setOnClickListener(this)

        // Стандартный метод возврата метода Фрагмента onCreateView
        return binding?.root
    }

    // Реализуем кнопку выхода
    // dismiss() - закрывает Боттом Сшит
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.exitIcon -> dismiss()
        }

    }
}