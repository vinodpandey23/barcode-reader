package com.barcode.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import com.google.zxing.multi.MultipleBarcodeReader;

/**
 * 
 * Barcode reader utility
 * 
 * @author Vinod Pandey
 *
 */
public class BarCodeReader {

	public static void main(String args[]) throws NotFoundException, IOException {

		String path = args[0];

		Result[] result = decodeQRCode(new File(path).toURI().toURL());

		System.out.println(result.length);

		for (Result r : result) {
			System.out.println(r.getText());
		}

	}

	/**
	 * Read multiple barcode from single image/tiff file
	 * 
	 * @param barCodeImage
	 *            url for barcode image file
	 * @return
	 * @throws IOException
	 * @throws NotFoundException
	 */
	private static Result[] decodeQRCode(URL barCodeImage) throws IOException, NotFoundException {

		BufferedImage bufferedImage = ImageIO.read(barCodeImage);
		LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

		Reader reader = new MultiFormatReader();
		MultipleBarcodeReader bcReader = new GenericMultipleBarcodeReader(reader);

		Result[] result = bcReader.decodeMultiple(bitmap);

		return result;
	}

}
