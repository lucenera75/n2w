package co.nz.rdf.n2w;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class N2wController {

    private static final String[] upTo19 = {
        "", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN",
        "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN",
        "EIGHTEEN", "NINETEEN"
    };

    private static final String[] x10after19 = {
        "",        
        "",        
        "TWENTY",  
        "THIRTY",  
        "FORTY",   
        "FIFTY",   
        "SIXTY",   
        "SEVENTY", 
        "EIGHTY",  
        "NINETY"   
    };

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam String name, @RequestParam String number, Model model) {
        try {
            String tokens = convert(number.trim());
            model.addAttribute("name", name);
            model.addAttribute("tokens", tokens);
            return "resultFragment";             
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errorFragment";         
        }
    }

    public static String convert(String number) {
        if (Double.parseDouble(number) == 0) {
            return "ZERO DOLLARS";
        }

        long integerPart;
        int decimalPart;
        if (number.indexOf(".") > 0) {
            String[] parts = number.split("\\.");
            integerPart = Long.parseLong(parts[0]);
            decimalPart = Integer.parseInt((parts[1] + "00").substring(0, 2));
        } else if (number.indexOf(".") == 0) {
            integerPart = 0;
            decimalPart = Integer.parseInt(((number + "00")).substring(1,3));
        } else {
            integerPart = Integer.parseInt(number);
            decimalPart = 0;
        }

        String strInt = convertIntegerPart(integerPart);
        String decStr = convertFractionalPart(decimalPart);

        return strInt + " AND " + decStr;
    }

    private static String convertUpToThousand(int num) {
        String tokens = "";
        if (num % 100 < 20) {
            tokens = upTo19[num % 100];
            num /= 100;
        } else {
            tokens = upTo19[num % 10];
            num /= 10;
            tokens = x10after19[num % 10] + " " + tokens;
            num /= 10;
        }
        if (num == 0) return tokens;
        return upTo19[num] + " HUNDRED " + tokens;
    }
    public static String convertIntegerPart(long num) {
        if (num == 0) return "ZERO DOLLARS";
        if (num == 1) return "ONE DOLLAR";
    
        String tokens = "";
    
        String[] decimalGroups = { "", "THOUSAND", "MILLION", "BILLION", "TRILLION" };
    
        int place = 0;
    
        while (num > 0) {
            if (num % 1000 != 0) {
                String segment = convertUpToThousand((int) (num % 1000));
                tokens = segment + " " + decimalGroups[place] + " " + tokens;
            }
            num /= 1000;
            place++;
        }
    
        return (tokens.trim() + " DOLLARS").trim();
    }

    private static String convertFractionalPart(int num) {
        if (num == 0) {
            return "ZERO CENTS";
        }

        String tokens = "";

        if (num < 20) {
            tokens += upTo19[num];
        } else {
            tokens += x10after19[num / 10];
            if ((num % 10) > 0) {
                tokens += upTo19[num % 10];
            }
        }

        tokens += " CENT" + (num == 1 ? "" : "S");

        return tokens.trim();
    }
}
