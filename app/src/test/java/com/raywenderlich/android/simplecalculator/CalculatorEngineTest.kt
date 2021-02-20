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
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class CalculatorEngineTest {

  private val engine = CalculatorEngine()

  @Before
  fun setup() {
    engine.clear()
  }

  @Test
  fun testForAddition() {
    engine.operator = '+'
    assertThat(engine.result, equalTo(0.toString()))

    // 0 + 10
    engine.calculate(10.toString())
    assertThat(engine.result, equalTo(10.toString()))

    // 10 + 10
    engine.calculate(10.toString())
    assertThat(engine.result, equalTo(20.toString()))

    // 20 + 10
    engine.calculate(10.toString())
    assertThat(engine.result, equalTo(30.toString()))
  }

  @Test
  fun testForSubtraction() {
    engine.operator = '+'
    // Setup initial value
    assertThat(engine.result, equalTo(0.toString()))
    engine.calculate(100.toString())
    assertThat(engine.result, equalTo(100.toString()))

    engine.operator = '-'

    // 100 - 10
    engine.calculate(10.toString())
    assertThat(engine.result, equalTo(90.toString()))

    // 90 - 50
    engine.calculate(50.toString())
    assertThat(engine.result, equalTo(40.toString()))

    // 40 - 50
    engine.calculate(50.toString())
    assertThat(engine.result, equalTo("-10"))
  }

  @Test
  fun testForMultiplication() {
    engine.operator = '*'
    // set initial value
    engine.result = "1"
    // 1 * 10
    engine.calculate(10.toString())
    assertThat(engine.result, equalTo(10.toString()))

    // 10 * 20
    engine.calculate(20.toString())
    assertThat(engine.result, equalTo(200.toString()))

    // 200 * 5
    engine.calculate(5.toString())
    assertThat(engine.result, equalTo(1000.toString()))
  }

  @Test
  fun testForDivision() {
    engine.operator = '/'
    // set initial value
    engine.result = "1000"

    // 1000 / 10
    engine.calculate(10.toString())
    assertThat(engine.result, equalTo(100.toString()))

    // 100 / 5
    engine.calculate(5.toString())
    assertThat(engine.result, equalTo(20.toString()))

    // 20 / 2
    engine.calculate(2.toString())
    assertThat(engine.result, equalTo(10.toString()))

    // 10 / 3
    engine.calculate(4.toString())
    assertThat(engine.result, equalTo("2.5"))

    // 2.5 / 2
    engine.calculate(2.toString())
    assertThat(engine.result, equalTo("1.25"))
  }

  @Test
  fun testForPower() {
    engine.operator = '^'
    // set initial value
    engine.result = "5"
    // 5 ^ 2
    engine.calculate(2.toString())
    assertThat(engine.result, equalTo(25.toString()))

    // 25 ^ 0.5
    engine.calculate(0.5.toString())
    assertThat(engine.result, equalTo(5.toString()))
  }
}
