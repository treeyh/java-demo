/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-17 下午9:12
 * LastModified: 18-5-12 下午2:55
 */

/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description: ip信息
 * Version: 1.0
 * Date: 18-5-9 下午2:09
 * LastModified: 18-5-9 下午2:09
 */

package com.ejyi.demo.springboot.server.manager.client.dto;

import lombok.Data;

@Data
public class IpInfoClientDTO {

    private String province;
    private String city;
    private Integer end;
    private String district;
    private String country;
    private String isp;
    private Integer ret;
    private Integer start;
    private String type;
    private String desc;

}
