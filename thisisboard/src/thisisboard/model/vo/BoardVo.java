package thisisboard.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardVo {
	private int bno;
	private String btitle;
	private String bcontent;
	private Date bdate;
	private String bwriter;
}
