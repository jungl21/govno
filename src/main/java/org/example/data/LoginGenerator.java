package org.example.data;

public class LoginGenerator {
    private static final String[] EN_LETTERS = {
            ".", "a", "b", "v", "g", "d", "e", "e", "j", "z", "i", "iy", "k", "l",
            "m", "n","o", "p", "r", "s","t", "u", "f", "h","ts", "ch", "sh", "sh", "","i", "", "e","u", "ya"
    };

    private static final String[] RU_LETTERS = {".", "а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л",
            "м", "н","о", "п", "р", "с","т", "у", "ф", "х","ц", "ч", "ш", "щ", "ъ","ы", "ь", "э","ю", "я"};

    public static String generatelogin(Stuff stuff) {
      var login = (stuff.getSurname() + "." + stuff.getName().substring(0,1) + "." +
        stuff.middle_name().substring(0,1)).toLowerCase();

      var correctLogin = new StringBuilder();

      for (var letter: login.split("")){
          for (var i = 0; i < RU_LETTERS.length; i ++) {
              if (letter.equals(RU_LETTERS[i])) {
                  correctLogin.append(EN_LETTERS[i]);
              }
          }
      }
      return correctLogin.toString();
    }

}
