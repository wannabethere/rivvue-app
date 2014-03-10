package com.yatrix.activity.service.utils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * long range = 1234567L; Random r = new Random() long number = (long)(r.nextDouble()*range); will
 * give you a long between 0 (inclusive) and range (exclusive). Similarly if you want a number
 * between x and y: long x = 1234567L; long y = 23456789L; Random r = new Random() long number =
 * x+((long)(r.nextDouble()*(y-x)));
 * 
 * @author tkmald2
 */
public class SUPCGenerator {

  /**
   * Encryption algorithm transformation.
   */
  private static final String TRIPLE_DES_TRANSFORMATION = "DESede/CBC/NoPadding";

  /**
   * Encryption algorithm.
   */
  private static final String ALGORITHM = "DESede";

  /**
   * Encryption provider.
   */
  private static final String BOUNCY_CASTLE_PROVIDER = "BC";

  /**
   * Encoding alphabet.
   */
  private static final String ALPHABET = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

  /**
   * Initialization vector.
   */
  private static final byte[] IV = { 0x1a, 0x01, 0x2f, 0x10, 0x45, 0x5a, 0x11, 0x12 };

  /**
   * Password salt.
   */
  private static final String SALT = "Er#178d$jaJash2Y38dh^0qW";

  /**
   * Salted password size.
   */
  private static final int PASSWORD_SIZE = 24;

  private static final long max = 2147483646L;

  private static Random random;
  /**
   * Init security provider.
   */
  static {
    Security.addProvider(new BouncyCastleProvider());
    random = new Random();
  }

  public static long getNextRandom() {
    long value = (long)(random.nextDouble() * max);
    ;
    return value;
  }

  /**
   * Creates SUPC promo code using SUPC sensitive data.
   * 
   * @param data
   *          SUPC sensitive data.
   * @param saltedPasswordBytes
   *          Salted password bytes array.
   * @return SUPC promo code.
   * @throws com.kohls.supc.Exception
   *           is raised if encryption steps are invalid.
   */
  public static String encrypt(long offerId, long promoCode) throws Exception {
    try {
      byte[] saltedPasswordBytes = getSaltedPassword().getBytes("UTF8");
      byte[] dataBytes = dataToBytes(offerId, promoCode);
      byte[] encrypted = encryptData(dataBytes, saltedPasswordBytes);
      return encode(encrypted);
    } catch (IllegalBlockSizeException e) {
      throw new Exception("SUPC encryption block size is invalid.", e);
    } catch (BadPaddingException e) {
      throw new Exception("SUPC encryption padding is invalid.", e);
    } catch (NoSuchAlgorithmException e) {
      throw new Exception("SUPC encryption algorithm is invalid.", e);
    } catch (NoSuchProviderException e) {
      throw new Exception("SUPC encryption provider is invalid.", e);
    } catch (NoSuchPaddingException e) {
      throw new Exception("SUPC encryption padding is invalid.", e);
    } catch (InvalidKeyException e) {
      throw new Exception("SUPC encryption key is invalid.", e);
    } catch (InvalidAlgorithmParameterException e) {
      throw new Exception("SUPC encryption algorithm is invalid.", e);
    }
  }

  /**
   * Apply encryption algorithm to sensitive data.
   * 
   * @param data
   *          Sensitive data.
   * @param saltedPasswordBytes
   *          Salted password in bytes.
   * @return Encrypted data.
   * @throws IllegalBlockSizeException
   *           is raised when input block has invalid size.
   * @throws BadPaddingException
   *           is raised if there is issues with algorithm padding.
   * @throws NoSuchAlgorithmException
   *           is raised if there is no such algorithm.
   * @throws NoSuchProviderException
   *           is raised if there is no such provider.
   * @throws NoSuchPaddingException
   *           is raised if there is no padding.
   * @throws InvalidKeyException
   *           is raised if key is invalid.
   * @throws InvalidAlgorithmParameterException
   *           is raised if algorithm parameters issues are found
   */
  private static byte[] encryptData(byte[] data, byte[] saltedPasswordBytes)
    throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
    NoSuchProviderException, NoSuchPaddingException, InvalidKeyException,
    InvalidAlgorithmParameterException {
    SecretKey keySpec = new SecretKeySpec(saltedPasswordBytes, ALGORITHM);
    Cipher cipher = Cipher.getInstance(TRIPLE_DES_TRANSFORMATION, BOUNCY_CASTLE_PROVIDER);
    cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(IV));
    return cipher.doFinal(data);
  }

  /**
   * Converts sensitive data to bytes array.
   * 
   * @param supcData
   *          SUPC sensitive data.
   * @return bytes array.
   */
  private static byte[] dataToBytes(long offerId, long promoCodeId) throws Exception {
    int[] data = { toSignedInt(offerId), toSignedInt(promoCodeId) };
    ByteBuffer buf = ByteBuffer.allocate(data.length * 4);
    IntBuffer intBuf = buf.asIntBuffer();
    intBuf.put(data);
    return buf.array();
  }

  /**
   * Encode encrypted data to SUPC promo code.
   * 
   * @param encryptedData
   *          Encrypted data.
   * @return SUPC promo code.
   * @throws com.kohls.supc.Exception
   *           is raised if SUPC encrypted data has invalid size
   */
  private static String encode(byte[] encryptedData) throws Exception {
    if (encryptedData.length != 8) {
      throw new Exception("SUPC encoding is invalid. Encrypted data should have size 8 bytes");
    }
    int[] encInt = new int[9];
    for (int i = 0; i < encryptedData.length; i++) {
      encInt[i] = encryptedData[i] + Byte.MAX_VALUE + 1;
    }
    encInt[8] = 1;
    return toPromo(toBytePromo(encInt));
  }

  /**
   * Converts int array to SUPC promo code.
   * 
   * @param source
   *          int array
   * @return SUPC promo code.
   */
  private static String toPromo(int[] source) {
    StringBuilder buffer = new StringBuilder();
    for (int code : source) {
      buffer.append(ALPHABET.charAt(code));
    }
    return buffer.toString();
  }

  /**
   * Converts unsigned value to signed integer.
   * 
   * @param value
   *          unsigned value
   * @return signed integer.
   * @throws com.kohls.supc.Exception
   *           is raised if input value has illegal size.
   */
  @SuppressWarnings("boxing")
  private static int toSignedInt(long value) throws Exception {
    if (value > Integer.MAX_VALUE || value < 0) {
      throw new Exception(String.format("SUPC input data conversion is invalid. "
        + "Value should be in range [%d, %d], but it is %d", 0, Integer.MAX_VALUE, value));
    }
    return (int)(value - Integer.MAX_VALUE - 1);
  }

  /**
   * Create SUPC promo code byte presentation from int presentation.
   * 
   * @param source
   *          int presentation
   * @return byte presentation.
   * @throws com.kohls.supc.Exception
   *           is raised if SUPC conversion is invalid.
   */
  @SuppressWarnings("boxing")
  private static int[] toBytePromo(int[] source) throws Exception {
    if (source.length != 9) {
      throw new Exception(
        "SUPC bytes to promo code conversion is invalid.. Array size should be 9 bytes");
    }
    int[] promo = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    byte promoByteId = 0;
    byte arrayIndex = 0;
    byte bitIndex = 7;
    byte promoBitId = 0;
    while (promoByteId < 13) {
      Boolean isSet = (source[arrayIndex] >> (7 - bitIndex) & 1) == 1;
      bitIndex--;
      if (isSet) {
        promo[promoByteId] += (1 << promoBitId);
      }
      promoBitId++;
      if (promoBitId == 5) {
        promoByteId++;
        promoBitId = 0;
      }
      if (bitIndex == -1) {
        bitIndex = 7;
        arrayIndex++;
      }
    }
    return promo;
  }

  /**
   * Prepare salted password.
   * 
   * @return salted password.
   */
  private static String getSaltedPassword() {
    String storedPass = "testing";

    int i = 0;
    int i1 = 0;
    int i2 = 0;
    StringBuilder builder = new StringBuilder();
    while (i < PASSWORD_SIZE) {
      boolean salted = false;
      if (i1 < storedPass.length()) {
        builder.append(storedPass.charAt(i1++));
        i++;
        salted = true;
      }
      if (i2 < SALT.length()) {
        builder.append(SALT.charAt(i2++));
        i++;
        salted = true;
      }

      if (!salted) {
        builder.append("x");
        i++;
      }
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    try {
      System.out.println(encrypt(12232, getNextRandom()));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
