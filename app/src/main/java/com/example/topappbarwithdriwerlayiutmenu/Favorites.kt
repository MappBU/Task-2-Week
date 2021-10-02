package com.example.topappbarwithdriwerlayiutmenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.topappbarwithdriwerlayiutmenu.databinding.FavoritesBinding

// Это обычный фрагмент
// Интерфейс нужен чтобы реализовать метож нажатия на кнопку play карточки
class Favorites : Fragment(), View.OnClickListener  {

    private var binding: FavoritesBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Вот так привязывается разметка во фрагменте
        binding = DataBindingUtil.inflate(inflater, R.layout.favorites, container, false)

        // Метод реализует долгое нажатие(Выбор) карточки
        binding?.card?.setOnLongClickListener {
            binding?.card?.isChecked = !binding?.card?.isChecked!!
            true
        }

        // Тап по кнопке Play через интерфейс
        binding?.playButton?.setOnClickListener(this)

        // Стандартный возврат метода фрагмента: onCreateView
        return binding?.root
    }

    // Реализуем таб по кнопке Play на карточке
    override fun onClick(view: View?) {
        // ??
        val details = Details()
        // ??
        val parameters = Bundle()

        // Передаю в Details (BottomSheetDialogFragment) значения карточки
        // Там я должен получить и вывести на DialogSheetFragment (Детальную инфу о фильме)
        parameters.putString("tittleMovie", binding?.titleMovieDetails?.text?.toString())
        parameters.putString("longMovie", binding?.longMovieDetails?.text?.toString())
        parameters.putString("actorsMovie", binding?.actorsMovieDetails?.text?.toString())

        // ??
        details.arguments = parameters

        // Наконец Показывает сам BottoScheetDialogFragment (Детальную инфу о фильме)
        details.show((context as FragmentActivity).supportFragmentManager, "details")
    }

}