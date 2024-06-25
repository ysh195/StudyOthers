1. https://github.com/naver/fixture-monkey/blob/9f732b5f8851197841e383ad67b7511f374784c8/README.md
   - 솔직히 이해 하나도 못함. 여러 생성자나 메서드가 복잡하게 얽혀있음
   - 근데 코드를 어떻게 구성 해야 하는지에 대해서는 조금 이해함.
   - 난 이걸 퍼즐로 이해했기 때문에, 하위에서 작은 퍼즐 조각을 하나하나 깎아서 중간 혹은 메인에 모아 놓고 한 번에 조립하는 방식이었음. 그렇기 때문에 중간이나 메인이 매우 길고 복잡함
   - 하지만 저 코드에서는 상위로 갈수록 간결하고, 생성자나 메서드 하나하나가 많은 매개변수를 취함.
   - 이건 퍼즐이라기보다는 아폴로 프로젝트에 가까움. 작은 부품들이 절차를 거침에 따라 자연스럽게 달라붙어서 하나의 거대한 부품을 이루고, 그게 중앙에서 합쳐져서 아주 거대한 무언가가 되는 방식
   - 그리고 매개변수가 너무 많거나 길면 엔터로 구분해 줌
   - 앞으로 매개변수를 통한 자연스러운 완성을 좀 더 신경써야 겠음
2. https://github.com/naver/arcus-java-client.git
 - src/main/java/net/spy/memcached/AddrUtil.java
