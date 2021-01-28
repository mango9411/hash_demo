import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author mango
 * @date 2021/1/28 15:51
 * @description: 一致性hash算法 + 虚拟节点
 */
public class ConsistentHashWithVirtual {

    public static void main(String[] args) {
        // 可排序map 
        SortedMap<Integer, String> sortedMap = new TreeMap<>();
        // 初始化 把服务器节点的ip哈希值对应到hash环上
        String[] tomcatServers = new String[]{"123.111.0.0", "123.101.3.1", "111.20.35.2", "123.98.26.3"};
        // 定义虚拟节点个数
        int virtualCount = 3;
        // 定义客户端IP
        String[] clients = new String[]{"10.78.12.3", "113.25.63.1", "126.12.3.8"};
        for (String tomcatServer : tomcatServers) {
            //求出hash值对应到hash环, 存储hash值与ip的对应关系
            int serverHash = Math.abs(tomcatServer.hashCode());
            sortedMap.put(serverHash, tomcatServer);
            for (int i = 0; i < virtualCount; i++) {
                int virtualServerHash = Math.abs((tomcatServer + "#" + i).hashCode());
                sortedMap.put(virtualServerHash, "由虚拟节点" + i + "映射" + tomcatServer);
            }
        }
        // 针对客户端IP求出hash值
        for (String client : clients) {
            int clientHash = Math.abs(client.hashCode());
            // 针对客户端找到能够处理当前客户端请求的服务器(按照环上顺时针最近原则)
            SortedMap<Integer, String> sortedMap1 = sortedMap.tailMap(clientHash);
            Integer integer;
            if (sortedMap1.isEmpty()) {
                //hash环上第一个
                integer = sortedMap.firstKey();
            } else {
                integer = sortedMap1.firstKey();
            }
            System.out.println("客户端:" + client + "被路由到服务器:" + sortedMap.get(integer));
        }
    }
}
