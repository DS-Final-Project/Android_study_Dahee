package com.example.prac_android

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.prac_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Kim da hee")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName

        binding.btnDone.setOnClickListener {
            //it은 done 버튼을 의미
            addNickName(it)
        }
        binding.tvNickname.setOnClickListener {
            //it은 nicknameTextView를 의미
            updateNickName(it)
        }
    }

    private fun addNickName(view: View) {
        //여기서 view는 done 버튼의 객체를 의미
        binding.apply {
            myName?.nickname = etNickname.text.toString()
            //바인딩 된 데이터가 변경 되었을 때 UI를 refresh 해준다.
            invalidateAll()
            etNickname.visibility = View.GONE
            btnDone.visibility = View.GONE
            tvNickname.visibility = View.VISIBLE
        }

        //done 버튼 클릭 시 키보드 숨기기
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.btnDone.windowToken,0)
    }

    private fun updateNickName(view: View) {

        binding.apply {
            etNickname.visibility = View.VISIBLE
            btnDone.visibility = View.VISIBLE
            etNickname.visibility = View.GONE
            etNickname.requestFocus()
        }

        //키보드 보이게 하기
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.etNickname, 0)
    }
}