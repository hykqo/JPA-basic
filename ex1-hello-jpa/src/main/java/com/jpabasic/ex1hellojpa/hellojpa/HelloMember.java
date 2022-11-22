package com.jpabasic.ex1hellojpa.hellojpa ;
import javax.persistence.*;

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
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "WORK_ZIPCODE"))
    })
    private Address workAddress;

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
}