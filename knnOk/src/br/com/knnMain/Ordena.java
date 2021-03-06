package br.com.knnMain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class knnMain {

	public static void main(String[] args) throws Exception {

		// Arrays referente a base de treinamento
		ArrayList<String> trainingSepalLenght = new ArrayList<String>();
		ArrayList<String> trainingSepalWidht = new ArrayList<String>();
		ArrayList<String> trainingPetalLength = new ArrayList<String>();
		ArrayList<String> trainingPetalWidht = new ArrayList<String>();
		ArrayList<String> trainingName = new ArrayList<String>();

		ArrayList<String> TESTE = new ArrayList<String>();

		// Arrays referente aos resultados
		ArrayList<String> resTrainingName = new ArrayList<String>();
		ArrayList<String> resTrainingRes = new ArrayList<String>();
		ArrayList<Double> resInTrainingRes = new ArrayList<Double>();

		// Arrays referente a base de teste
		ArrayList<String> testSepalLenght = new ArrayList<String>();
		ArrayList<String> testSepalWidht = new ArrayList<String>();
		ArrayList<String> testPetalLength = new ArrayList<String>();
		ArrayList<String> testPetalWidht = new ArrayList<String>();
		ArrayList<String> testName = new ArrayList<String>();

		// Arrays referente as distancias

		ArrayList<Double> sepalLenght_Training = new ArrayList<Double>();
		ArrayList<Double> sepalWidht_Training = new ArrayList<Double>();
		ArrayList<Double> petalLength_Training = new ArrayList<Double>();
		ArrayList<Double> petalWidht_Training = new ArrayList<Double>();

		ArrayList<Double> sepalLenght_Test = new ArrayList<Double>();
		ArrayList<Double> sepalWidht_Test = new ArrayList<Double>();
		ArrayList<Double> petalLength_Test = new ArrayList<Double>();
		ArrayList<Double> petalWidht_Test = new ArrayList<Double>();

		// ABRINDO ARQUIVO DE TREINAMENTO
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream("Newiris.data.csv")));
		String linha = null; // VARIAVEL VERIFICA O FIM DA LINHA

		while ((linha = reader.readLine()) != null) {
			// QUANDO ENCONTAR A VIRGULA IR� JOGAR EM OUTRA COLUNA
			String[] trainingBase = linha.split(",");

			// INSERE CADA VALOR EM SUA ARRAY LIST
			trainingSepalLenght.add(trainingBase[0]);
			trainingSepalWidht.add(trainingBase[1]);
			trainingPetalLength.add(trainingBase[2]);
			trainingPetalWidht.add(trainingBase[3]);
			trainingName.add(trainingBase[4]);

		}
		// FECHA O ARQUIVO BASE DE TREINAMENTO
		reader.close();

		System.out
				.println("###############################################################");

		// ABRINDO ARQUIVO DE TESTE
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(
				new FileInputStream("testIris.data.csv")));
		String linha2 = null; // VARIAVEL VERIFICA O FIM DA LINHA

		while ((linha2 = reader2.readLine()) != null) {
			// QUANDO ENCONTAR A VIRGULA IR� JOGAR EM OUTRA COLUNA
			String[] testBase = linha2.split(",");

			// INSERE CADA VALOR EM SUA ARRAY LIST
			testSepalLenght.add(testBase[0]);
			testSepalWidht.add(testBase[1]);
			testPetalLength.add(testBase[2]);
			testPetalWidht.add(testBase[3]);
			testName.add(testBase[4]);

		}
		// FECHANDO ARQUIVO DE TESTE
		reader2.close();

		// TRABALHANDO COM A BASE DE TREINAMENTO
		System.out.printf("Percorrendo  a BASE DE TREINAMENTO\n");
		int n = trainingName.size();// PEGANDO O TAMANHO DO ARQUIVO
		int i;
		for (i = 0; i < n; i++) {
			System.out.printf("SepalLenght %d- %s\n", i,
					trainingSepalLenght.get(i));
			System.out.printf("SepalWidht %d- %s\n", i,
					trainingSepalWidht.get(i));
			System.out.printf("PetalLenght %d- %s\n", i,
					trainingPetalLength.get(i));
			System.out.printf("PetalWidht %d- %s\n", i,
					trainingPetalWidht.get(i));
			System.out.printf("Class %d- %s\n", i, trainingName.get(i));
			System.out.println("############################");
		}

		int nn = testName.size();// PEGANDO O TAMANHO DO ARQUIVO
		int cont;
		int cont2;
		String matriz[][] = new String[n][2];

		/* CRIANDO ARQUIVO QUE IR� ARMAZENAR OS RESULTADOS */

		FileWriter arq = new FileWriter("result.csv");
		PrintWriter gravarArq = new PrintWriter(arq);

		for (cont = 0; cont < nn; cont++) {
			sepalLenght_Test.add(Double.parseDouble(testSepalLenght.get(cont)));
			sepalWidht_Test.add(Double.parseDouble(testSepalWidht.get(cont)));
			petalLength_Test.add(Double.parseDouble(testPetalLength.get(cont)));
			petalWidht_Test.add(Double.parseDouble(testPetalWidht.get(cont)));

		}

		for (cont = 0; cont < n; cont++) {
			sepalLenght_Training.add(Double.parseDouble(trainingSepalLenght
					.get(cont)));
			sepalWidht_Training.add(Double.parseDouble(trainingSepalWidht
					.get(cont)));
			petalLength_Training.add(Double.parseDouble(trainingPetalLength
					.get(cont)));
			petalWidht_Training.add(Double.parseDouble(trainingPetalWidht
					.get(cont)));

			matriz[cont][0] = trainingName.get(cont);

			/*
			 * FAZENDO OS CALCULOS DAS DIST�NCIAS E PREENCGENCO A COLUNA EM QUE
			 * IR�O SER ARMAZENADAS
			 */

			for (cont2 = 0; cont2 < nn; cont2++) {
				matriz[cont][1] = Double.toString(Math.sqrt(Math.abs(Math.pow(
						sepalLenght_Test.get(cont2)
								- sepalLenght_Training.get(cont), 2)
						+ Math.pow(sepalWidht_Test.get(cont2)
								- sepalWidht_Training.get(cont), 2)
						+ Math.pow(petalLength_Test.get(cont2)
								- petalLength_Training.get(cont), 2)
						+ Math.pow(petalWidht_Test.get(cont2)
								- petalWidht_Training.get(cont), 2))

				));
				for (int coluna = 0; coluna < 2; coluna++) {
					/*
					 * TESTE.add(matriz[cont][coluna]); Collections.sort(TESTE);
					 */gravarArq.print(" " + matriz[cont][coluna]);

				}
				gravarArq.print("\n");
			}

		}
		arq.close();

		// ABRINDO ARQUIVO RESULTADO
		BufferedReader reader3 = new BufferedReader(new InputStreamReader(
				new FileInputStream("result.csv")));
		String linha3 = null; // VARIAVEL VERIFICA O FIM DA LINHA
		while ((linha3 = reader3.readLine()) != null) {
			// QUANDO ENCONTAR A VIRGULA IR� JOGAR EM OUTRA COLUNA
			String[] resBase = linha3.split(" ");
			linha3.trim();

			// INSERE CADA VALOR EM SUA ARRAY LIST
			resTrainingName.add(resBase[1]);
			resTrainingRes.add(resBase[2]);

		}

		// FECHA O ARQUIVO RESULTADO
		reader3.close();

		
		  final String[][] matriz2 = new String[1269][2];
	
		for (cont = 0; cont < 1269; cont++) {
			matriz2[cont][0] = resTrainingName.get(cont);
			for (cont2 = 0; cont2 < nn; cont2++) {
				matriz2[cont][1] = resTrainingRes.get(cont);
			}

		}
		

	    final Comparator<String[]> arrayComparator = new Comparator<String[]>() {
	        public int compare(String[] o1, String[] o2) {
	            return o1[1].compareTo(o2[1]);
	        }
	    };
	    Arrays.sort(matriz2, arrayComparator);
	    int count =0;
	    String x = null;
	    
		 int k;
		 Scanner valorK = new Scanner(System.in);
		 System.out.println("Entre com o Valor de K"); k = valorK.nextInt();
		
	    for (final String[] arr : matriz2) {
	       
	   	if(count<k){
	    	
	    	System.out.println(count+"-->"+Arrays.toString(arr));
	    	
	       }
	           
	        count++;
	    }	

      
	}

}
