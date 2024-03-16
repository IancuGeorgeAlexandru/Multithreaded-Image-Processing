package packTest;

import java.io.IOException;
import java.io.*;

import packWork.*;

public class MyMain {

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		InputFile inputFile = new InputFile();
		OutputFile outputFile = new OutputFile(inputFile.getFileName());
		long endTime = System.currentTimeMillis();
		
		System.out.println("Citirea fisierelor a durat:  " + (endTime - startTime) + " milliseconds");

		PipedOutputStream pipeOut = new PipedOutputStream();
		PipedInputStream pipeIn = new PipedInputStream(pipeOut);

		DataOutputStream out = new DataOutputStream(pipeOut);
		DataInputStream in = new DataInputStream(pipeIn);

		Image img = new Image(inputFile.getFileName(), false);
		Buffer b = new Buffer();
		Producator p1 = new Producator(b, img);
		WriterResult w1 = new WriterResult(img, outputFile.getFileName(), in);
		Consumator c1 = new Consumator(b, img, outputFile.getFileName(), out);

		p1.start();
		c1.start();
		w1.start();

		ImageDisplay inputImgDisp = new ImageDisplay(inputFile.getFileName(), "Imaginea pe un singur thread initiala");
	}
}
