package network.tcp.autocloseable;

public class ResourceCloseMainV2 {
    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        ResourceV1 resource1 = new ResourceV1("resource1");
        ResourceV1 resource2 = new ResourceV1("resource2");

        try {
            resource1.call();
            resource2.callEx();  // CallException, 핵심 예외
        } catch (CallException e) {
            System.out.println("ex: " + e);
            throw e;
        } finally {
            System.out.println("자원 정리");
            if (resource2 != null) {
                resource2.closeEx();    // 부가 예외 (예외가 바뀜)
            }
            if (resource1 != null) {
                resource1.close();  // 호출 안됨
            }
        }
    }
}
