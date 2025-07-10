package com.roys.wolvnote.common

import android.icu.text.DecimalFormat

object SalaryCalculation {
    fun calculate(amount: String): String{
        val salary: Double = amount.toDouble()
        val expenditure = (salary*47.5)/100
        val alms = (salary*2.5)/100
        val instalment = (salary*30)/100
        val savings = (salary*20)/100
        val decimalFormatter = DecimalFormat("#,##0")

        val expenditureString = decimalFormatter.format(expenditure)
        val almsString = decimalFormatter.format(alms)
        val instalmentString = decimalFormatter.format(instalment)
        val savingsString = decimalFormatter.format(savings)

        return "Expenditure: "+ expenditureString + "\n" +
                "Alms: " + almsString + "\n" +
                "Instalment: " + instalmentString + "\n" +
                "Savings: " + savingsString
    }
}