package practice;


    // 自定义测试类
    class User {
        // 静态代码块：类初始化时执行
        static {
            System.out.println("User类初始化");
        }
    }

    public class getClass {
        public static void main(String[] args) throws Exception {
            // 方式1：类名.class
            Class<User> c1 = User.class;
            System.out.println("方式1：" + c1);

            // 方式2：对象.getClass()
            User user = new User();
            Class<?> c2 = user.getClass();
            System.out.println("方式2：" + c2);

            // 方式3：Class.forName()
            Class<?> c3 = Class.forName("practice.User"); // 替换为你的全类名
            System.out.println("方式3：" + c3);

            // 方式4：类加载器.loadClass()
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            Class<?> c4 = loader.loadClass("practice.User");
            System.out.println("方式4：" + c4);
        }
    }
