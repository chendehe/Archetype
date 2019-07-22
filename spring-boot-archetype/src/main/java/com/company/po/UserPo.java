package com.company.po;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPo implements BasePo<UserPo>, Serializable {

  private static final long serialVersionUID = -2535178899662614143L;
  private String id;
  private String name;
  private int gender;
  private LocalDate birthday;
  private String address;
  private Date createTime;
  private Date updateTime;

}
