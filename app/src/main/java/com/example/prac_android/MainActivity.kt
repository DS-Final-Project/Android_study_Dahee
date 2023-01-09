package com.example.prac_android

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_done).setOnClickListener {
            //it은 done 버튼을 의미
            addNickName(it)
        }
    }

    private fun addNickName(view: View) {
        //여기서 view는 done 버튼의 객체를 의미
        val editText = findViewById<EditText>(R.id.et_nickname)
        val nicknameTextView = findViewById<TextView>(R.id.tv_nickname)

        nicknameTextView.text = editText.text
        editText.visibility = View.GONE
        //view는 done 버튼을 의미
        view.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE

        //done 버튼 클릭 시 키보드 숨기기
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
    }
}