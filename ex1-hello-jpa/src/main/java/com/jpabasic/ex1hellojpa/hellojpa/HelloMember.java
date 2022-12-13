package com.jpabasic.ex1hellojpa.hellojpa ;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "HELLO_MEMBER")
 public class HelloMember extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @Embedded
    private Period workPeriod = null; //null로 지정할경우 Period 클래스의 값은 전부 null로 기입된다.
    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME") //칼럼이 id제외하고 하나밖에 없으면 JPA가 예외적으로 해당 컬럼을 만들어준다.
    private Set<String> favoriteFoods = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addresseHistory = new ArrayList<>();

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public Period getWorkPeriod() {
      return workPeriod;
   }

   public void setWorkPeriod(Period workPeriod) {
      this.workPeriod = workPeriod;
   }

   public Address getHomeAddress() {
      return homeAddress;
   }

   public void setHomeAddress(Address homeAddress) {
      this.homeAddress = homeAddress;
   }

    public Set<String>
    getFavoriteFoods() {
        return favoriteFoods;
    }

    public List<Address> getAddresseHistory() {
        return addresseHistory;
    }
}