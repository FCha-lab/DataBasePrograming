package com.example.databaseprograming;

import android.text.InputFilter;
import android.text.Spanned;

public class NoSpaceInputFilter implements InputFilter {
    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        // 입력된 문자열에 공백이 있는지 확인
        for (int i = start; i < end; i++) {
            if (Character.isWhitespace(source.charAt(i))) {
                return ""; // 공백이 있으면 빈 문자열로 바꿔줌
            }
        }
        return null; // 공백이 없으면 입력을 허용
    }
}
