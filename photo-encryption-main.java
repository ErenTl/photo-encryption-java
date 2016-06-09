import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import java.util.Scanner;


public class main {
	
	static int[][] orjinal;
	static int[][] anahtar;
	static int[][] sifreli;
	static int[][] cozunen;
	static int[][] okunan_sifreli;
	 
	
	static BufferedImage image;
	BufferedImage sifreli_img;
	static BufferedImage secilen_sifre;
	static BufferedImage secilen_anahtar;
	BufferedImage anahtar_img;
	BufferedImage cozulen_img;
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		main s1 = new main();
		
		//File file = new File("C:/Users/eren/Desktop/erentl/tl.png");
		//File output = new File("C:/Users/eren/Desktop/erentl/sifreli.jpg");
		/*if(!output.exists()) {
			output.createNewFile();
		}*/
		
		System.out.println("ErenTl'nin best şifreleme uygulamasına hoşgeldiniz.");
		System.out.println("Şifrelemek için 1 yazınız.");
		System.out.println("Şifrelenmiş fotoğrafı anahtar ile açmak için 2 ye basınız.");
		int secim = input.nextInt();
		if(secim==1) {
			System.out.println("Resim seçiniz.");
			s1.ResimSec();
			orjinal=new int[image.getWidth()][image.getHeight()];
			anahtar=new int[image.getWidth()][image.getHeight()];
			sifreli=new int[image.getWidth()][image.getHeight()];
			s1.OrjinalOku();
			s1.Anahtar();
			System.out.println("Anahtar nereye kaydedilsin?");
			s1.AnahtarBas();
			s1.Sifrele();
			System.out.println("Şifre nereye kaydedilsin?");
			s1.SifreBas();
		}
		if(secim==2) {
			s1.SifreliSec();
			okunan_sifreli=new int[secilen_sifre.getWidth()][secilen_sifre.getHeight()];
			anahtar		=new int[secilen_anahtar.getWidth()][secilen_anahtar.getHeight()];
			cozunen		=new int[secilen_anahtar.getWidth()][secilen_anahtar.getHeight()];
			s1.SifreliOku();
			s1.SifreCoz();
			s1.CozBastır();
			
		}
		
		
		
		
	}
	
	void ResimSec() {
				
		try {
			
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(chooser);
			
			System.out.println(chooser.getSelectedFile().getPath());
			image = ImageIO.read(chooser.getSelectedFile());
			
			
		} catch (IOException e) {
			e.printStackTrace();
			ResimSec();
		}
	}
	
	public void OrjinalOku() {
		
		for (int i = 0; i < orjinal.length; i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i,j));
				int red=color.getRGB();
				orjinal[i][j]=red;
				//System.out.println(orjinal[i][j]);
			}
		}
		
	}
	
	public void Anahtar() {
		
		Random rdm = new Random();

		for (int i = 0; i < anahtar.length; i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				anahtar[i][j]=( (int)(rdm.nextInt()^rdm.nextInt()));
				//System.out.println(anahtar[i][j]);
			}
			
		}
		
	}
	
	public void AnahtarBas() {
		
		try {
					
					JFileChooser save = new JFileChooser();
					save.showOpenDialog(save);
					anahtar_img = new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_INT_RGB );
					
					for (int i = 0; i < sifreli.length; i++) {
						for (int j = 0; j < image.getHeight(); j++) {
							anahtar_img.setRGB(i, j, anahtar[i][j]);
						}
						
					}
					
					FileOutputStream fos = new FileOutputStream(save.getSelectedFile());
					ImageIO.write(anahtar_img, "PNG", fos);
					
					
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					AnahtarBas();
				} catch (IOException e) {
					e.printStackTrace();
					AnahtarBas();
				}
		
	}
	
	public void Sifrele() {
		for (int i = 0; i < sifreli.length; i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				//System.out.println("orjinal: "+ orjinal[i][j]);
				//System.out.println("anahtar: "+ anahtar[i][j]);
				
				//sifreli[i][j]=anahtar[i][j]&(orjinal[i][j]^anahtar[i][j]);
				sifreli[i][j]=orjinal[i][j]^anahtar[i][j];
				//System.out.println("şifreli: "+ sifreli[i][j]);
			}
			
		}
			
	}
	
	public void SifreBas() {
				
		try {
			
			JFileChooser save = new JFileChooser();
			save.showOpenDialog(save);
			sifreli_img = new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_INT_RGB );
			
			for (int i = 0; i < sifreli.length; i++) {
				for (int j = 0; j < image.getHeight(); j++) {
					sifreli_img.setRGB(i, j, sifreli[i][j]);
				}
				
			}
			
			FileOutputStream fos = new FileOutputStream(save.getSelectedFile());
			ImageIO.write(sifreli_img, "PNG", fos);
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			SifreBas();
		} catch (IOException e) {
			e.printStackTrace();
			SifreBas();
		}
		
	}
	
	public void SifreliSec() {
		
		
		try {
			JFileChooser chooser = new JFileChooser();
			
			System.out.println("Şifreyi seçiniz.");
			chooser.showOpenDialog(chooser);
			System.out.println(chooser.getSelectedFile().getPath());
			secilen_sifre = ImageIO.read(chooser.getSelectedFile());
			
			System.out.println("Anahtarı giriniz.");
			chooser.showOpenDialog(chooser);
			System.out.println(chooser.getSelectedFile().getPath());
			secilen_anahtar = ImageIO.read(chooser.getSelectedFile());

		} catch (IOException e) {
			e.printStackTrace();
			SifreliSec();
		}
	}
	
	public void SifreliOku() {
			
			for (int i = 0; i < okunan_sifreli.length; i++) {
				for (int j = 0; j < secilen_sifre.getHeight(); j++) {
					Color colorsifre = new Color(secilen_sifre.getRGB(i,j));
					okunan_sifreli[i][j]=colorsifre.getRGB();
					
					Color coloranahtar = new Color(secilen_anahtar.getRGB(i,j));
					anahtar[i][j]=coloranahtar.getRGB();
				}
			}
			
		}
	
	public void SifreCoz() {
		
		for (int i = 0; i < okunan_sifreli.length; i++) {
			for (int j = 0; j < secilen_anahtar.getHeight(); j++) {
				cozunen[i][j]=anahtar[i][j]^okunan_sifreli[i][j];
			}
		}
	}

	
	public void CozBastır() {
		
		try {
			
			JFileChooser save = new JFileChooser();
			System.out.println("Resim nereye kaydedilsin?");
			save.showOpenDialog(save);
			cozulen_img = new BufferedImage(secilen_sifre.getWidth(),secilen_sifre.getHeight(), BufferedImage.TYPE_INT_RGB );
			
			for (int i = 0; i < okunan_sifreli.length; i++) {
				for (int j = 0; j < secilen_sifre.getHeight(); j++) {
					cozulen_img.setRGB(i, j, cozunen[i][j]);
				}
				
			}
			
			FileOutputStream fos = new FileOutputStream(save.getSelectedFile());
			ImageIO.write(cozulen_img, "PNG", fos);
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			CozBastır();
		} catch (IOException e) {
			e.printStackTrace();
			CozBastır();
		}
	}
	
	
}
