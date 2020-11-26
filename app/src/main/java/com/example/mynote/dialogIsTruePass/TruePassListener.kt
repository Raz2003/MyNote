package com.example.mynote.dialogIsTruePass

import com.example.mynote.data.Notes

interface TruePassListener {
    fun truePass(dataItem : Notes)
    fun falsePass()
}