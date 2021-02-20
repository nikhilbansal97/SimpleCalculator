/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.simplecalculator

import CalculatorEngine
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

  private val engine by lazy {
    CalculatorEngine()
  }

  private val operands = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".")

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    registerButtonClick()
    textResult.isSelected = true
  }

  /**
   * Set [View.OnClickListener] on all the views
   */
  private fun registerButtonClick() {
    (layoutButtonHolder as ViewGroup).children.forEach {
      it.setOnClickListener(this)
    }
  }

  @SuppressLint("SetTextI18n")
  override fun onClick(v: View?) {
    // Calculator is in invalid state so reset before doing any calculations
    if (engine.isInInvalidState) {
      resetCalculator()
    }

    // Every button must have a tag
    if (v == null || v.tag == null) {
      return
    }
    when (val tag = v.tag.toString()) {
      "del" -> {
        // Delete last character from input
        val oldValue = readInputValue()
        if (oldValue.isNotEmpty()) {
          editTextInput.setText(oldValue.substring(0, oldValue.lastIndex))
        }
      }
      "C" -> resetCalculator()
      in operands -> {
        // Handle numerical values
        // Two decimals in a single number is not allowed. Handle invalid inputs
        if (readInputValue().contains(".") && tag == ".") {
          return
        }
        editTextInput.setText(readInputValue() + tag)
      }
      else -> {
        // Handle operator buttons and equal button

        // Calculate
        val result = engine.calculate(readInputValue())
        // Set result
        textResult.text = result
        // Reset input field
        editTextInput.setText("")

        if (tag != "=") {
          // if input is not "=", then it must be operators (like +, -, ^, etc)
          engine.operator = tag[0]
          textOperator.text = tag
        }
      }
    }
  }

  private fun resetCalculator() {
    engine.clear()
    editTextInput.setText("")
    textOperator.text = engine.operator.toString()
    textResult.text = "0"
  }

  private fun readInputValue() = editTextInput.text.toString()
}
