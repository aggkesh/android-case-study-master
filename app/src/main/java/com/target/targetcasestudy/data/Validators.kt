package com.target.targetcasestudy.data

import android.util.Log
import androidx.core.text.isDigitsOnly

/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */
fun validateCreditCard(creditCardNumber: String): Boolean {

    return creditCardNumber.takeIf { it.length in 13..19 && it.isDigitsOnly() }?.let { cardNumber ->
        cardNumber.slice(IntRange(0, cardNumber.length - 2)).reversed()
            .map { Character.getNumericValue(it) }.mapIndexed { index, it ->
                var digit = it

                if (index % 2 == 0) digit = digit.times(2)

                if (digit > 9) digit = digit.minus(9)

                digit
            }
            .fold(Character.getNumericValue(cardNumber[cardNumber.lastIndex])) { acc, i -> acc + i }
            .rem(10)
            .equals(0)
    } ?: false

}