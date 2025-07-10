package com.roys.wolvnote.common

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

fun generateSecretKey() {
    val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, Constants.PROVIDER)
    val parameterSpec = KeyGenParameterSpec.Builder(
        Constants.KEY_ALIAS,
        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    )
        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        .build()

    keyGenerator.init(parameterSpec)
    keyGenerator.generateKey()
}
fun encryptData(plaintext: String): String {
    val keyStore = KeyStore.getInstance(Constants.PROVIDER).apply { load(null) }
    val secretKey = keyStore.getKey(Constants.KEY_ALIAS, null) as SecretKey

    val cipher = Cipher.getInstance("AES/GCM/NoPadding")
    cipher.init(Cipher.ENCRYPT_MODE, secretKey)
    val iv = cipher.iv
    val cipherText = cipher.doFinal(plaintext.toByteArray(Charsets.UTF_8))

    val combined = iv + cipherText
    return Base64.encodeToString(combined, Base64.NO_WRAP)

}

fun decryptData(encryptedData: String): String {
    val keyStore = KeyStore.getInstance(Constants.PROVIDER).apply { load(null) }
    val secretKey = keyStore.getKey(Constants.KEY_ALIAS, null) as SecretKey

    val combined = Base64.decode(encryptedData, Base64.NO_WRAP)
    val iv = combined.copyOfRange(0, 12)
    val cipherText = combined.copyOfRange(12, combined.size)

    val cipher = Cipher.getInstance("AES/GCM/NoPadding")
    val spec = GCMParameterSpec(128, iv)
    cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)

    val plaintextBytes = cipher.doFinal(cipherText)
    return String(plaintextBytes, Charsets.UTF_8)
}
