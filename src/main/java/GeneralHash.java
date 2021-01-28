/**
 * @author mango
 * @date 2021/1/28 15:39
 * @description: 普通hash算法
 */
public class GeneralHash {

    public static void main(String[] args) {
        // 定义客户端IP
        String[] clients = new String[]{"10.78.12.3", "113.25.63.1", "126.12.3.8"};

        //定义服务器集群数量
        int serverCount = 5;

        //普通hash算法
        ordinaryHash(clients, serverCount);
    }

    /**
     * 普通hash
     *
     * @param clients
     * @param serverCount
     */
    public static void ordinaryHash(String[] clients, int serverCount) {
        // 路由计算
        for (String client : clients) {
            // 对ip计算hash值
            int hash = Math.abs(client.hashCode());

            // 路由的服务器编号
            int index = hash % serverCount;

            System.out.println("客户端：" + client + "被路由到服务器：" + index);
        }
    }
}
