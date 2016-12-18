package convenientadditions.api;

import java.util.ArrayList;
import java.util.List;

public class SimpleSearchQuery {
    public static final char NOT='!';
    public static final char LIT='"';
    public static final char ESC='\\';
    public static final char SPC=' ';

    //format: "literal" !not !"not literal" single words here
    public static List<String> getQueryResult(String query,List<String> stringList,boolean caseSensitive){
        List<String> literals=new ArrayList<>();
        List<String> banned=new ArrayList<>();
        List<String> ret=new ArrayList<>();

        boolean literal=false;
        boolean not=false;
        boolean esc=false;
        String currentLiteral="";
        for(char c:query.toCharArray()) {
            if(esc) {
                currentLiteral += c;
                esc=false;
                continue;
            }
            if (c==SPC) {
                if (literal) {
                    currentLiteral += c;
                } else {
                    if (currentLiteral.length()!=0) {
                        if (not)
                            banned.add(currentLiteral);
                        else
                            literals.add(currentLiteral);
                        not = false;
                        currentLiteral="";
                    }
                }
            }else if(c==LIT){
                if(literal){
                    literal=false;
                    if (not)
                        banned.add(currentLiteral);
                    else
                        literals.add(currentLiteral);
                    not = false;
                    currentLiteral="";
                }else if(currentLiteral.length()==0){
                    literal=true;
                }else{
                    currentLiteral += c;
                }
            }else if(c==NOT){
                if(!literal&&currentLiteral.length()==0&&!not){
                    not=true;
                }else{
                    currentLiteral += c;
                }
            }else if(c==ESC){
                esc=true;
            }else{
                currentLiteral += c;
            }
        }
        if (currentLiteral.length()!=0) {
            if (not)
                banned.add(currentLiteral);
            else
                literals.add(currentLiteral);
        }

        list: for (String s:stringList) {
            for(String l:literals){
                if(caseSensitive) {
                    if (!s.contains(l))
                        continue list;
                }else if(!s.toLowerCase().contains(l.toLowerCase()))
                        continue list;
            }
            for(String b:banned){
                if(caseSensitive) {
                    if (s.contains(b))
                        continue list;
                }else if(s.toLowerCase().contains(b.toLowerCase()))
                    continue list;
            }
            ret.add(s);
        }
        return ret;
    }
}
