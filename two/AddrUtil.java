// this is not mine and from [ https://github.com/naver/arcus-java-client - src/main/java/net/spy/memcached/AddrUtil.java ]
// I don't have any rights related with this code.

package net.spy.memcached;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Convenience utilities for simplifying common address parsing.
 */
public final class AddrUtil {

  private AddrUtil() {} // 생성자를 그냥 막아놨네?

  /**
   * Split a string containing whitespace or comma separated host or
   * IP addresses and port numbers of the form "host:port host2:port"
   * or "host:port, host2:port" into a List of InetSocketAddress
   * instances suitable for instantiating a MemcachedClient.
   *
   * Note that colon-delimited IPv6 is also supported.
   * For example:  ::1:11211
   */
  public static List<InetSocketAddress> getAddresses(String s) { // 그리고 메서드만 열어 놨네? 생성자 없이 익명 객체로만 메서드를 실행하라는 건가?
    if (s == null) { // 입력값이 없으면
      throw new NullPointerException("Null host list"); // 예외를 new로 만드는 것도 가능하구나
    }
    ArrayList<InetSocketAddress> addrs = // ArrayList이면서 InetSocketAddress를 항목으로 갖는 생성자
            new ArrayList<InetSocketAddress>();

    if (s.trim().equals("")) { // 양쪽 끝 공백을 제거했는데 ""로 값이 따로 없으면 그냥 addrs만 리턴
      return addrs;
    }

    for (String hoststuff : s.split("(?:\\s|,)+")) { // 입력 받은 문자열 s를 spilt으로 쪼개고
      if (hoststuff.equals("")) { // 해당 값(hoststuff)이 ""면 배열의 다음 순서로 넘어감
        continue;
      }

      int finalColon = hoststuff.lastIndexOf(':'); // 해당 값(hoststuff) 내의 ":" 중 가장 뒤에 있는 것의 위치를 저장
      if (finalColon < 1) { // 근데 이게 없거나(= -1) 맨 앞(= 0)이면 new 예외로 처리
        throw new IllegalArgumentException("Invalid server ``"
                + hoststuff + "'' in list:  " + s);

      }
      // ":" 중 맨 뒤에 있는 것을 기준으로  
      String hostPart = hoststuff.substring(0, finalColon); // 앞에 있는 문자열은 호스트고 
      String portNum = hoststuff.substring(finalColon + 1); // 뒤에 있는 문자열은 포트 넘버

      addrs.add(new InetSocketAddress(hostPart, // 아까 생성한 addrs에 새로운 InetSocketAddress를 생성해서 추가
              Integer.parseInt(portNum)));
    }
    assert !addrs.isEmpty() : "No addrs found"; // isEmpty는 비어 있으면 true, 채워져 있으면 false를 리턴하니까 !를 붙이면 채워져 있으면 true, 비어 있으면 false
    // 그리고 assert는 해당 조건이 참이면 넘어가고, 거짓이면 해당 값을 출력하니까 값이 비어 있을 때 "No addrs found" 출력
    return addrs; // 그냥 내부에 이러한 저장 과정을 거치는 특수한 ArrayList<InetSocketAddress>를 만들고 싶었던 거구나
  }

  public static InetSocketAddress getAddress(String s) {
    return getAddresses(s).get(0); // getAddresses가 static 메서드니까 바로 실행 가능함. InetSocketAddress를 저장하는 ArrayList의 요소니까 당연히 리턴 타입은 InetSocketAddress
    // get(0) List 클래스의 함수로, List 내의 인덱스가 0인(맨 앞에 있는) 개체를 출력. 근데 굳이 0을 가져오는 이유가 있나?
    // List에 들어가는 항목이 엄청 많을 텐데, 차라리 번호도 따로 입력 받아서 해당 개체 출력하게 하는 게 좋지 않나? 그냥 제대로 만들어졌는지
    // static 때문에 고정되어 같은 메서드인 이상 내부에 있는 List도 아마 동일 객체일 거임. 그러니 생성된 객체 내의 값을 가져옴으로써 그냥 잘 생성 됐는지 확인하는 건가?
  }
}
