package network.tcp.autocloseable;

// 문제점
// 1. resource 변수를 선언하면서 동시에 할당 불가
// 2. catch 이후 finally 호출, 자원 정리가 조금 늦어짐
// 3. 개발자가 실수로 close()를 호출하지 않을 수 있음
// 4. 개발자가 close() 호출 순서 실수, 보통 자원을 생성한 순서와 반대로 닫는다.
public class ResourceCloseMainV3 {
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
            // 자원 정리 시점에서 발생한 예외는 해결이 불가하므로 로깅만함.
            System.out.println("자원 정리");
            if (resource2 != null) {
                try {
                    resource2.closeEx();
                } catch (CloseException e) {
                    System.out.println("close ex: " + e);
                }
            }
            if (resource1 != null) {
                try {
                    resource1.closeEx();  // 호출 안됨
                } catch (CloseException e) {
                    System.out.println("close ex: " + e);
                }
            }
        }
    }
}
