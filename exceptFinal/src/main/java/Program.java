import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Program {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<String> enterArray = new ArrayList<String>();
        String tmp = "";

        System.out.println("Введите через ПРОБЕЛ: \n Имя, Отчество, Фамилия,\n" +
                " дата рождения в формате дд.мм.гггг,\n" +
                " номер телефона одним числом без дополнительных знаков,\n" +
                "пол в формате f или m");
        //0-имя, 1-отчество, 2-фамилия,  3-дата рождения, 4-телефон, 5-пол
        tmp = scanner.nextLine();
        enterArray = Arrays.asList(tmp.strip().split(" "));
        int arrSize = checkSize(enterArray);

        if ((arrSize!= -2) & (arrSize!= -1)){
            try {
                checkDate(enterArray.get(3));
            }
            catch (ParseException e){
                throw new RuntimeException("Неверный формат даты");
            }

            try{
                checkPhone(enterArray.get(4));
            }catch(NumberFormatException e){
                throw new RuntimeException("Неверный формат телефонного номера");
            }

            checkGender(enterArray.get(5));
            String filename = enterArray.get(2) + ".txt";
            //0-имя, 1-отчество, 2-фамилия, 3-дата рождения, 4-телефон, 5-пол
            //<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
            String newString =
                    enterArray.get(2) + " " +
                    enterArray.get(0) + " " +
                    enterArray.get(1) + " " +
                    enterArray.get(3) + " " +
                    enterArray.get(4) + " " +
                    enterArray.get(5);

            fileWrite(filename, newString);

        }else{
            System.out.println("Неверное количество входных данных");
        }

    }
    static void fileWrite(String fileName, String str){
        try (FileWriter fileWriter = new FileWriter(fileName, true)){
            fileWriter.write(str+"\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    static void checkGender(String s) throws RuntimeException{
        if (!(s.equals("f")|s.equals("m"))){
            throw new RuntimeException("Неверно введен пол");
        }
    }

    static int checkPhone(String phone) throws NumberFormatException {
        return Integer.parseInt(phone);
    }

    static Date checkDate(String str) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        return formatter.parse(str);
    }

    static int checkSize(List list){
        if (list == null)
            return -2;
        if (list.size() != 6){
            return -1;
        }
        return list.size();
    }

}
