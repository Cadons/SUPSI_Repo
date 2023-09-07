package ch.supsi.sweng2.ex4;

import javax.xml.crypto.dsig.Transform;

public class CTransform implements NumberTransformer {
    @Override
    public String transform(int v) {

        if (v >= 1) {
            //from 1 to v create a string with fizz multiple of 3 buzz multiple of 5 fizzbuzz multiple of 3 and 5 and return the final string
            StringBuilder result = new StringBuilder();
            for (int i = 1; i <= v; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    result.append("fizzbuzz");
                } else if (i % 3 == 0) {
                    result.append("fizz");
                } else if (i % 5 == 0) {
                    result.append("buzz");
                } else {
                    result.append(i);
                }
            }
            return result.toString();
        } else {

            throw new RuntimeException("v must be >= 1");
        }

    }

}
