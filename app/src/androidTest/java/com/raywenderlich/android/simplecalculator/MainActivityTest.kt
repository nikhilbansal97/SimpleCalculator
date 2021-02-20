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

import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class MainActivityTest : TestCase() {

  @get:Rule
  val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

  @Test
  fun testMainActivity() {
    before {
      activityTestRule.launchActivity(null)
      testLogger.i("Starting test")
    }.after {
    }.run {
      step("Test setup") {
        MainScreen {
          button0.hasTag("0")
          button1.hasTag("1")
          button2.hasTag("2")
          button3.hasTag("3")
          button4.hasTag("4")
          button5.hasTag("5")
          button6.hasTag("6")
          button7.hasTag("7")
          button8.hasTag("8")
          button9.hasTag("9")
          buttonDecimal.hasTag(".")
          buttonPlus.hasTag("+")
          buttonMinus.hasTag("-")
          buttonDivide.hasTag("/")
          buttonMultiply.hasTag("*")
          buttonPower.hasTag("^")
          buttonDelete.hasTag("del")
          buttonClear.hasTag("C")
        }
      }
      step("Test for input") {
        MainScreen {
          button0.click()
          button1.click()
          button2.click()
          editTextInput.hasText("012")

          buttonPlus.click()
          textOperator.hasText("+")
          textResult.hasText("12")
          editTextInput.hasText("")
        }
      }
      step("When clicked clear") {
        MainScreen {
          buttonClear.click()
          editTextInput.hasText("")
          textOperator.hasText("+")
          textResult.hasText("0")
        }
      }
      step("When clicked delete") {
        MainScreen {
          buttonClear.click()
          button1.click()
          button2.click()
          button3.click()
          button4.click()
          editTextInput.hasText("1234")
          buttonDelete.click()
          editTextInput.hasText("123")
          buttonDelete.click()
          editTextInput.hasText("12")
        }
      }
      step("Handle decimal") {
        MainScreen {
          buttonClear.click()
          button1.click()
          button2.click()
          button3.click()
          button4.click()
          editTextInput.hasText("1234")
          buttonDecimal.click()
          editTextInput.hasText("1234.")
          button4.click()
          editTextInput.hasText("1234.4")
          buttonDecimal.click()
          editTextInput.hasText("1234.4")
          buttonDecimal.click()
          buttonDecimal.click()
          buttonDecimal.click()
          editTextInput.hasText("1234.4")
        }
      }
      step("Handle error") {
        MainScreen {
          buttonClear.click()
          button1.click()
          buttonDivide.click()
          button0.click()
          buttonEquals.click()
          textResult.hasText("Err")
        }
      }
    }
  }
}
