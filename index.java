import com.telbiz.model.SMSHeader;
import com.telbiz.sdk.Telbiz;
public class MainDemo {

	public static void main(String[] args) {
		
		//Construct Clientid: xxxxxxxx, Secret: xxxxxxxxx
		Telbiz telbiz = new Telbiz("16563166388434084", "2d345bf0-c91a-4766-8a6c-6fccd42c86d3");
		//Topup
		var resultTopup = telbiz.Topup("2022248509", 10000);
		System.out.println(resultTopup.Code + /*"\n"+ resultTopup.Message + */"\n"+ resultTopup.Detail + "\n"+ resultTopup.Success);
		
		
	}
}
