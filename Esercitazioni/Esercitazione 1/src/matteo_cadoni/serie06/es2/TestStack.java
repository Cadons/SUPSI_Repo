package matteo_cadoni.serie06.es2;

public class TestStack {
    public static void main(String[] args) {
        CustomStack stack=new CustomStack();
        System.out.println("Is list empty?:"+stack.isEmpty());

        stack.push(10);
        stack.push(120);
        stack.push(2.5);
        stack.push("Hello");
        stack.push("Hello Java");
        stack.push(-9820);
        stack.push(true);
        stack.push(new CustomStack());
        stack.push("Java Stack");
        System.out.println("Is list empty?:"+stack.isEmpty());
        System.out.println("Last Element:"+stack.peek().get());
        System.out.println("Remove last element:"+stack.pop().get());
        System.out.println("Last Element:"+stack.peek().get());

        class test implements AdvancedStack.Evaluate{//test string text
            String txt="Hello";
            @Override
            public boolean verify(Object objectToTest) {
                if(objectToTest instanceof String)
                {
                    if(((String) objectToTest).contains(txt))
                    {
                        return true;
                    }
                }
                return false;
            }
        }
        System.out.println("Hello string:"+stack.count(new test()));
        System.out.println("Numbers:"+stack.count(new AdvancedStack.Evaluate() {
            @Override
            public boolean verify(Object objectToTest) {
                if(objectToTest instanceof Number)
                {
                    return  true;
                }
                return false;
            }
        }));
        System.out.println("Integer:"+stack.count(new AdvancedStack.Evaluate() {
            @Override
            public boolean verify(Object objectToTest) {
                if(objectToTest instanceof Integer)
                {
                    return true;
                }
                return false;
            }
        }));
        System.out.println("Double or Float > 0.5:"+stack.count(new AdvancedStack.Evaluate() {
            @Override
            public boolean verify(Object objectToTest) {
                if(objectToTest instanceof Float || objectToTest instanceof Double)
                {
                    if(((Double)objectToTest)>0.5)
                        return true;
                }
                return false;
            }
        }));
    }
}
