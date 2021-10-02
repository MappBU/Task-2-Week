package com.example.topappbarwithdriwerlayiutmenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.topappbarwithdriwerlayiutmenu.databinding.FavoritesBinding
import com.example.topappbarwithdriwerlayiutmenu.databinding.UploadBinding

class Upload : Fragment(), View.OnClickListener  {

    private var binding: UploadBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.upload, container, false)

        binding?.card?.setOnLongClickListener {
            binding?.card?.isChecked = !binding?.card?.isChecked!!
            true
        }

        // Тап по кнопке Play через интерфейс
        binding?.playButton?.setOnClickListener(this)

        return binding?.root
    }

    override fun onClick(view: View?) {
        val details = Details()

        val parameters = Bundle()

        // Передаю во фрагмент Техт вью из Маин активити разметки
        parameters.putString("tittleMovie", binding?.titleMovieDetails?.text?.toString())
        parameters.putString("longMovie", binding?.longMovieDetails?.text?.toString())
        parameters.putString("actorsMovie", binding?.actorsMovieDetails?.text?.toString())

        details.arguments = parameters

        // Показывает Bottom Scheet Dialog Fragment
        details.show((context as FragmentActivity).supportFragmentManager, "details")
    }

}