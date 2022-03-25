import java.util.ArrayList;
import java.util.Scanner;

public class PinKodu {
	
	static String tarih;  // 6 "Sevecen ve romantiksiniz. Tam bir salon insanısınızdır."
	// 7 "Duygusal, yaratıcı, mahremiyetlerine düşkünsünüz. Elde edilmesi zorsunuz ve İlişkiye girdiğiniz insana gerçek bir bağlılık gösterirsiniz."
  	static String[] ozellikler = {
			  "Lider, yaratıcı, eleştirilere tahammülsüz, çabuk parlayıp çabuk sönen birisiniz. İletişim alanında başarılısınız. Adalet duygularınız gelişmiştir."
			, "Genel olarak çok verici, kendilerinden çok karşıdakini düşünen ailelerine düşkün, temkinli ve sezgisel bir insansınız. Mükemmel ebeveynsinizdir."
			, "Organize, tutkulu, istediklerini elde etmek için düşmanca davranmaktan çekinmeyen kişisiniz."
			, "Esrarengiz, hedefleri olan, tanımlanması güçsünüz. Kendinize özgü tarzınız var."
			, "Zarif ve dışa dönüksünüz. Zihinsel ve fiziksel olarak sürekli hareket halinde ve mizahi, yenilikçi bir kişisiniz."
			, "Sevecen ve romantiksiniz. Tam bir salon insanısınızdır."
			, "Duygusal, yaratıcı, mahremiyetlerine düşkünsünüz. Elde edilmesi zorsunuz ve İlişkiye girdiğiniz insana gerçek bir bağlılık gösterirsiniz."
			, "Sabırlı, başarılı, azimlisiniz. Şefkat ihtiyacı hissedersiniz. Zarif yüz hatlarınız olduğu için ilerleyen yaşlarında da genç görünürsünüz"
			, "Saf, masum, kolay güvenenirsiniz. Ne var ki sezgi güçleri sayesinde sanılanın aksine kolay kandırılamassınız. Kendinize olan güvene rağmen yine de yaptıklarını onaylatan birisisiniz."};
	
	

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Lütfen doğum tarihinizi giriniz. (DD.MM.YYYY)");
		tarih = sc.next();
		
		while(!tarih.matches("[\\d]{2}.[\\d]{2}.[\\d]{4}")) {
			System.out.println("GECERSİZ FORMAT.");
			tarih = sc.next();
		}
		
		
		hesaplamalar();
		
	}
	
	
	
	public static int[] indexes() {   // Scannerdan alınan tarihi int arrayine dönüştürmek için
	
		String a = tarih.replaceAll("[.]", "");  // TARİHİ . SIZ ŞEKİLDE BİR STRİNG YAPTIK (30.01.2001 -> 30012001)
			
			int indexes[] = new int[a.length()];
			
			
			for (int i = 0; i < a.length(); i++) {  // BÜTÜN RAKAMLARI, INDEXES ADLI INT ARRAYE, ÇEVİRİLEN STRİNGİN HER HARFİ İLE EKLEDİK
				indexes[i] = a.charAt(i)-48;
			}  
			
			return indexes;   // return indexes = {3,0,0,1,2,0,0,1}     //30.01.2001
	}
	
	
	public static int duzenle (int a, int b) {  // Eğer 2 rakamın toplamı 10 dan büyükse rakamları toplayıp teke indirmek için
		int sum = a+b;
		
		if (sum>=10) {   //EĞER SAYI 10 DAN BÜYÜKSE
			String newNumber = Integer.toString(sum); //SAYISI STRİNGE CEVİR
			sum = (newNumber.charAt(0)-48) + (newNumber.charAt(1)-48); //HER HANESİNİ TEK TEK ALARAK TOPLA
		}
		
		return sum;
	}
	
	
  
	
	public static void printOneTime (ArrayList <String> arr) { //aynı özellikten 2 tane varsa 1 tane yazdırmak için
	
	    for(int j=0; j<arr.size(); j++){
	        boolean shouldPrint = true;
	       for (int i=0; i<j; i++){
	            if(arr.get(j).equals(arr.get(i))){
	                shouldPrint = false;
	          }
	        }
	       
	       if (shouldPrint == true) {
	             System.out.println(arr.get(j));
	       }
	   }
   }
	   
	
	public static void hesaplamalar() {
		
		ArrayList <String> kisi = new ArrayList<String>(); //KİSİYE OZEL PİN KODU OZELLİKLERİ
		int herHaneSonucu [] = new int[8];  //ilk switchdeki case4,5,6,7,8 için ve pin kodunu gösterebilmemiz icin oluşturuldu

		
		for (int j = 1; j <= 8; j++) {
			
			int toplam=0;
			
			switch (j) {			//BURDAKİ SWİTCHE GÖRE HER HANEYE ÖZEL FORMÜLLE SAYIYI BUL
			
			case 1: {  //dogum günleri toplamı ('30' .01.2001)
				toplam = indexes()[0] + indexes()[1];
				if(toplam>=10) {toplam = duzenle(indexes()[0] , indexes()[1]);}
				herHaneSonucu[0] = toplam;
				break;
			}
			
			case 2: { //dogulan ayın rakamlarının toplamı (30. '01' .2001)
				if(indexes()[2] == 0) toplam = indexes()[3];
				else {toplam = duzenle(indexes()[2] , indexes()[3]);}  //10. 11. 12. aylar için
				herHaneSonucu[1] = toplam;
				break;
			}
			
			case 3: { //dogulan yılın rakamlarının toplamı (2+0+0+1)
				toplam = indexes()[4] + indexes()[5] + indexes()[6] + indexes()[7];
				if(toplam >= 10) {
			//1998           duzenle(1,9) = 1                           , duzenle(9,8) = 17 =>7+1 = 8      duzenle(1,8) = 9
			//2019           duzenle(2,0) = 2                           , duzenle(1,9) = 10 =>1+0 = 1      duzenle(2,1) = 3
					toplam = duzenle(duzenle(indexes()[4],indexes()[5]) , duzenle(indexes()[6],indexes()[7]));
				}
				herHaneSonucu[2] = toplam;
				break;
			}
			
			case 4: {  //ilk 3 hane toplamı
				toplam = herHaneSonucu[0] + herHaneSonucu[1] + herHaneSonucu[2]; 
				if(toplam>=10) {toplam = duzenle(duzenle(herHaneSonucu[0],herHaneSonucu[1]) , herHaneSonucu[2]);}
				herHaneSonucu[3] = toplam;
				break;
			}
			
			case 5: {	//1. hane + 4.hane	toplamı			
				toplam = herHaneSonucu[0] + herHaneSonucu[3];
				if(toplam>=10) {toplam = duzenle(herHaneSonucu[0] , herHaneSonucu[3]);}
				herHaneSonucu[4] = toplam;
				break;
			}
			
			case 6: {  //1.hane + 2. hane toplamı
				toplam = herHaneSonucu[0] + herHaneSonucu[1];
				if(toplam>=10) {toplam = duzenle(herHaneSonucu[0] , herHaneSonucu[1]);}
				herHaneSonucu[5]= toplam;
				break;
			}
			
			case 7: { //2. hane + 3.hane toplamı
				toplam = herHaneSonucu[1] + herHaneSonucu[2];
				if(toplam>=10) {toplam = duzenle(herHaneSonucu[1] , herHaneSonucu[2]);}
				herHaneSonucu[6]= toplam;
				break;
			}
			
			case 8: { //6.hane + 7.hane toplamı
				toplam = herHaneSonucu[5] + herHaneSonucu[6];
				if(toplam>=10) {toplam = duzenle(herHaneSonucu[5] , herHaneSonucu[6]);}
				herHaneSonucu[7]=toplam;
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + j);
			}
		
		
			
			switch (toplam) {		//BURDAKİ SWİTCHE GÖRE HER HANEDEKİ SAYIYA EŞ OLAN ÖZELLİĞİ kisi ARRAYLISTINE YERLEŞTİR 
			case 1: {						
				kisi.add(ozellikler[0]);
				break;
			}
			case 2: {
				kisi.add(ozellikler[1]);
				break;
			}
			case 3: {
				kisi.add(ozellikler[2]);
				break;
			}
			case 4: {
				kisi.add(ozellikler[3]);
				break;
			}
			case 5: {
				kisi.add(ozellikler[4]);
				break;
			}
			case 6: {
				kisi.add(ozellikler[5]);
				break;
			}
			case 7: {
				kisi.add(ozellikler[6]);
				break;
			}
			case 8: {
				kisi.add(ozellikler[7]);
				break;
			}
			case 9:{
				kisi.add(ozellikler[8]);
				break;
			}
			
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + j);
			}
		
	}

		
		
		System.out.println("-------PIN KODUNUZ------- ");
		for (int i = 0; i < herHaneSonucu.length; i++) {
			System.out.print(" "+herHaneSonucu[i]);
		}
		
		System.out.println();
		
		
		System.out.println("-------Pin kodunuza göre özellikleriniz-------".toUpperCase());
		printOneTime(kisi);
 }

}








