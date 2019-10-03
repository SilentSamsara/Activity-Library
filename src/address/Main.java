package address;
import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.security.AlgorithmParameterGenerator;

import com.alibaba.fastjson.JSON;

import java.io.*;
public class Main {
	public static class AddressLibrary {
		//												1		2		3		3		4		5		6		7		8	9		10	11		12		13		14	15		16		17	18		19				20	21		22		23	24
		public static final String[]	provinces	= { "北京", "天津", "河北", "山西", "内蒙古自治区", "辽宁", "吉林", "黑龙江", "上海", "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东", "广西壮族自治区", "海南", "重庆", "四川", "贵州", "云南",
				"西藏自治区", "陕西", "甘肃", "青海", "宁夏回族自治区", "新疆维吾尔自治区", "台湾", "香港", "澳门"};
//				25			26		27		28	29				30				32	33		34
		
		public static final String[][]	citys	= { { "北京" }, { "天津" }, { "石家庄", "唐山", "秦皇岛", "邯郸", "邢台", "保定", "张家口", "承德", "沧州", "廊坊", "衡水" },
				{ "太原", "大同", "阳泉", "长治", "晋城", "朔州", "晋中", "运城", "忻州", "临汾", "吕梁" }, { "呼和浩特", "包头", "乌海", "赤峰", "通辽", "鄂尔多斯", "呼伦贝尔", "巴彦淖", "乌兰察布", "兴安盟", "锡林郭勒盟", "阿拉善盟" },
				{ "沈阳", "大连", "鞍山", "抚顺", "本溪", "丹东", "锦州", "营口", "阜新", "辽阳", "盘锦", "铁岭", "朝阳", "葫芦岛" }, { "长春", "吉林", "四平", "辽源", "通化", "白山", "松原", "白城", "延边" },
				{ "哈尔滨", "齐齐哈尔", "鸡西", "鹤岗", "双鸭山", "大庆", "伊春", "佳木斯", "七台河", "牡丹江", "黑河", "绥化", "大兴安岭" }, { "上海" },
				{ "南京", "无锡", "徐州", "常州", "苏州", "南通", "连云港", "淮安", "盐城", "扬州", "镇江", "泰州", "宿迁" }, { "杭州", "宁波", "温州", "嘉兴", "湖州", "绍兴", "金华", "衢州", "舟山", "台州", "丽水" },
				{ "合肥", "芜湖", "蚌埠", "淮南", "马鞍山", "淮北", "铜陵", "安庆", "黄山", "滁州", "阜阳", "宿州", "巢湖", "六安", "亳州", "池州", "宣城" }, { "福州", "厦门", "莆田", "三明", "泉州", "漳州", "南平", "龙岩", "宁德" },
				{ "南昌", "景德镇", "萍乡", "九江", "新余", "鹰潭", "赣州", "吉安", "宜春", "抚州", "上饶" },
				{ "济南", "青岛", "淄博", "枣庄", "东营", "烟台", "潍坊", "济宁", "泰安", "威海", "日照", "莱芜", "临沂", "德州", "聊城", "滨州", "菏泽" },
				{ "郑州", "开封", "洛阳", "平顶山", "安阳", "鹤壁", "新乡", "焦作", "濮阳", "许昌", "漯河", "三门峡", "南阳", "商丘", "信阳", "周口", "驻马店" },
				{ "武汉", "黄石", "十堰", "宜昌", "襄樊", "鄂州", "荆门", "孝感", "荆州", "黄冈", "咸宁", "随州", "恩施" },
				{ "长沙", "株洲", "湘潭", "衡阳", "邵阳", "岳阳", "常德", "张家界", "益阳", "郴州", "永州", "怀化", "娄底", "湘西" },
				{ "广州", "韶关", "深圳", "珠海", "汕头", "佛山", "江门", "湛江", "茂名", "肇庆", "惠州", "梅州", "汕尾", "河源", "阳江", "清远", "潮州", "揭阳", "云浮", "东莞" },
				{ "南宁", "柳州", "桂林", "梧州", "北海", "防城港", "钦州", "贵港", "玉林", "百色", "贺州", "河池", "来宾", "崇左" }, { "海口" }, { "重庆" },
				{ "成都", "自贡", "攀枝花", "泸州", "德阳", "绵阳", "广元", "遂宁", "内江", "乐山", "南充", "眉山", "宜宾", "广安", "达州", "雅安", "巴中", "资阳", "阿坝", "甘孜", "凉山" },
				{ "贵阳", "六盘水", "遵义", "安顺", "铜仁", "黔西南", "毕节", "黔东南", "黔南" }, { "昆明", "曲靖", "玉溪", "保山", "昭通", "丽江", "普洱", "临沧", "楚雄", "红河", "文山", "西双版纳", "大理", "德宏", "怒江", "迪庆" },
				{ "拉萨", "昌都", "山南", "日喀则", "那曲", "阿里", "林芝" }, { "西安", "铜川", "宝鸡", "咸阳", "渭南", "延安", "汉中", "榆林", "安康", "商洛" },
				{ "兰州", "金昌", "白银", "天水", "武威", "张掖", "平凉", "酒泉", "庆阳", "定西", "陇南", "临夏", "甘南" }, { "西宁", "海东", "海北", "黄南", "海南", "果洛", "玉树", "海西" },
				{ "银川", "石嘴山", "吴忠", "固原", "中卫" }, { "乌鲁木齐", "克拉玛依", "吐鲁番", "哈密", "昌吉", "博尔塔拉", "巴音郭楞", "阿克苏", "克孜勒苏柯尔克孜自治州", "喀什", "和田", "伊犁哈萨克", "塔城地", "阿勒泰", "自治区直辖县" },
				{"台北",  "高雄",  "基隆",  "新竹",  "台中",  "嘉义",  "台南",},
				{ "香港" }, { "澳门" } };
		
		public static final String[][][]	countys	= {
				{ { "东城", "西城", "崇文", "宣武", "朝阳", "丰台", "石景山", "海淀", "门头沟", "房山", "通州", "顺义", "昌平", "大兴", "怀柔", "平谷", "密云", "延庆" } },
				{ { "和平区", "河东区", "河西区", "南开区", "河北区", "红桥", "塘沽", "汉沽", "大港", "东丽", "西青", "津南", "北辰", "武清", "宝坻", "宁河", "静海", "蓟县" } },
				{ { "长安区", "桥东区", "桥西区", "新华区", "井陉矿区", "裕华区", "井陉", "正定", "栾城", "行唐", "灵寿", "高邑", "深泽", "赞皇", "无极", "平山", "元氏", "赵县", "辛集", "藁城", "晋州", "新乐", "鹿泉" },
				{ "路南区", "路北区", "古冶区", "开平", "丰南", "丰润", "滦县", "滦南", "乐亭", "迁西", "玉田", "唐海", "遵化", "迁安" }, { "海港区", "山海关", "北戴河", "青龙", "昌黎", "抚宁", "卢龙" },
				{ "邯山", "丛台", "复兴区", "峰峰矿区", "邯郸", "临漳", "成安", "大名", "涉县", "磁县", "肥乡", "永年", "邱县", "鸡泽", "广平", "馆陶", "魏县", "曲周", "武安" },
				{ "桥东区", "桥西区", "邢台", "临城", "内丘", "柏乡", "隆尧", "任县", "南和", "宁晋", "巨鹿", "新河", "广宗", "平乡", "威县", "清河", "临西", "南宫", "沙河" },
				{ "新市区", "北市区", "南市区", "满城", "清苑", "涞水", "阜平", "徐水", "定兴", "唐县", "高阳", "容城", "涞源", "望都", "安新", "易县", "曲阳", "蠡县", "顺平", "博野", "雄县", "涿州", "定州", "安国", "高碑店" },
				{ "桥东区", "桥西区", "宣化", "下花园区", "宣化", "张北", "康保", "沽源", "尚义", "蔚县", "阳原", "怀安", "万全", "怀来", "涿鹿", "赤城", "崇礼" },
				{ "双桥区", "双滦区", "鹰手营子矿区", "承德", "兴隆", "平泉", "滦平", "隆化", "丰宁", "宽城", "围场" },
				{ "新华", "运河区", "沧县", "青县", "东光", "海兴", "盐山", "肃宁", "南皮", "吴桥", "献县", "孟村", "泊头", "任丘", "黄骅", "河间" }, { "安次区", "广阳区", "固安", "永清", "香河", "大城", "文安", "大厂", "霸州", "三河" },
				{ "桃城区", "枣强", "武邑", "武强", "饶阳", "安平", "故城", "景县", "阜城", "冀州", "深州" } },
				{ { "小店区", "迎泽区", "杏花岭", "尖草坪", "万柏林", "晋源", "清徐", "阳曲", "娄烦", "古交" }, { "城区", "矿区", "南郊区", "新荣", "阳高", "天镇", "广灵", "灵丘", "浑源", "左云", "大同" },
				{ "城区", "矿区", "郊区", "平定", "盂县" }, { "城区", "郊区", "长治", "襄垣", "屯留", "平顺", "黎城", "壶关", "长子", "武乡", "沁县", "沁源", "潞城" }, { "城区", "沁水", "阳城", "陵川", "泽州", "高平" },
				{ "朔城", "平鲁", "山阴", "应县", "右玉", "怀仁" }, { "榆次区", "榆社", "左权", "和顺", "昔阳", "寿阳", "太谷", "祁县", "平遥", "灵石", "介休" },
				{ "盐湖", "临猗", "万荣", "闻喜", "稷山", "新绛", "绛县", "垣曲", "夏县", "平陆", "芮城", "永济", "河津" },
				{ "忻府区", "定襄", "五台", "代县", "繁峙", "宁武", "静乐", "神池", "五寨", "岢岚", "河曲", "保德", "偏关", "原平" },
				{ "尧都", "曲沃", "翼城", "襄汾", "洪洞", "古县", "安泽", "浮山", "吉县", "乡宁", "大宁", "隰县", "永和", "蒲县", "汾西", "侯马", "霍州" },
				{ "离石区", "文水", "交城", "兴县", "临县", "柳林", "石楼", "岚县", "方山", "中阳", "交口", "孝义", "汾阳" } },
				{ { "新城区", "回民区", "玉泉", "赛罕区", "土默特", "托克托", "和林格尔", "清水河", "武川" }, { "东河区", "昆都仑区", "青山区", "石拐区", "白云鄂博矿区", "九原", "土默特", "固阳", "达尔罕茂明安联合旗" },
				{ "海勃湾区", "海南区", "乌达区" }, { "红山区", "元宝山", "松山区", "阿鲁科尔沁旗", "巴林左旗", "巴林右旗", "林西", "克什克腾旗", "翁牛特旗", "喀喇沁旗", "宁城", "敖汉旗" },
				{ "科尔沁", "科尔沁左翼中旗", "科尔沁左翼后旗", "开鲁", "库伦旗", "奈曼旗", "扎鲁特旗", "霍林郭勒" }, { "东胜", "达拉特旗", "准格尔旗", "鄂托克前旗", "鄂托克旗", "杭锦旗", "乌审旗", "伊金霍洛旗" },
				{ "海拉尔", "阿荣旗", "莫力达瓦达斡尔族", "鄂伦春", "鄂温克族自", "陈巴尔虎旗", "新巴尔虎左旗", "新巴尔虎右旗", "满洲里", "牙克石", "扎兰屯", "额尔古纳", "根河" }, { "临河", "五原", "磴口", "乌拉特前旗", "乌拉特中旗", "乌拉特后旗", "杭锦后旗" },
				{ "集宁", "卓资", "化德", "商都", "兴和", "凉城", "察哈尔右翼前旗", "察哈尔右翼中旗", "察哈尔右翼后旗", "四子王旗", "丰镇" }, { "乌兰浩特", "阿尔山", "科尔沁右翼前旗", "科尔沁右翼中旗", "扎赉特", "突泉" },
				{ "二连浩特", "锡林浩特", "阿巴嘎旗", "苏尼特左旗", "苏尼特右旗", "东乌珠穆沁旗", "西乌珠穆沁旗", "太仆寺旗", "镶黄旗", "正镶白旗", "正蓝旗", "多伦" }, { "阿拉善左旗", "阿拉善右旗", "额济纳旗" } },
				{ { "和平区", "沈河", "大东", "皇姑", "铁西", "苏家屯", "东陵", "沈北新区", "于洪", "辽中", "康平", "法库", "新民" }, { "中山区", "西岗", "沙河口", "甘井子", "旅顺口", "金州", "长海", "瓦房店", "普兰店", "庄河" },
				{ "铁东区", "铁西区", "立山", "千山", "台安", "岫岩", "海城" }, { "新抚", "东洲", "望花", "顺城", "抚顺", "新宾", "清原" }, { "平山", "溪湖", "明山", "南芬", "本溪", "桓仁" },
				{ "元宝区", "振兴", "振安", "宽甸", "东港", "凤城" }, { "古塔区", "凌河", "太和", "黑山", "义县", "凌海", "北镇" }, { "站前区", "西市区", "鲅鱼圈", "老边区", "盖州", "大石桥" },
				{ "海州", "新邱", "太平区", "清河门", "细河", "阜新", "彰武" }, { "白塔区", "文圣区", "宏伟区", "弓长岭", "太子河", "辽阳", "灯塔" }, { "双台", "兴隆台", "大洼", "盘山" },
				{ "银州", "清河", "铁岭", "西丰", "昌图", "调兵山", "开原" }, { "双塔区", "龙城", "朝阳", "建平", "喀喇沁左翼", "北票", "凌源" }, { "连山", "龙港", "南票", "绥中", "建昌", "兴城" } },
				{ { "南关", "宽城", "朝阳区", "二道区", "绿园区", "双阳", "农安", "九台", "榆树", "德惠" }, { "昌邑", "龙潭", "船营", "丰满", "永吉", "蛟河", "桦甸", "舒兰", "磐石" },
				{ "铁西", "铁东", "梨树", "伊通", "公主岭", "双辽" }, { "龙山", "西安区", "东丰", "东辽" }, { "东昌区", "二道江", "通化", "辉南", "柳河", "梅河口", "集安" }, { "八道江", "江源", "抚松", "靖宇", "长白", "临江" },
				{ "宁江", "前郭尔罗斯", "长岭", "乾安", "扶余" }, { "洮北", "镇赉", "通榆", "洮南", "大安" }, { "延吉", "图们", "敦化", "珲春", "龙井", "和龙", "汪清", "安图" } },
				{ { "道里", "南岗", "道外", "平房", "松北", "香坊", "呼兰", "阿城区", "依兰", "方正", "宾县", "巴彦", "木兰", "通河", "延寿", "双城", "尚志", "五常" },
				{ "龙沙", "建华", "铁锋", "昂昂溪", "富拉尔基", "碾子山", "梅里斯达", "龙江", "依安", "泰来", "甘南", "富裕", "克山", "克东", "拜泉", "讷河" }, { "鸡冠", "恒山", "滴道区", "梨树区", "城子河", "麻山", "鸡东", "虎林", "密山" },
				{ "向阳", "工农区", "南山区", "兴安", "东山", "兴山", "萝北", "绥滨" }, { "尖山", "岭", "四方台", "宝山", "集贤", "友谊", "宝清", "饶河" }, { "萨尔图", "龙凤", "让胡路", "红岗", "大同", "肇州", "肇源", "林甸", "杜尔伯特" },
				{ "伊春", "南岔", "友好区", "西林区", "翠峦", "新青", "美溪", "金山屯", "五营", "乌马河", "汤旺河", "带岭", "乌伊岭", "红星", "上甘岭", "嘉荫", "铁力" },
				{ "向阳", "前进", "东风区", "郊区", "桦南", "桦川", "汤原", "抚远", "同江", "富锦" }, { "新兴区", "桃山区", "茄子河", "勃利" }, { "东安", "阳明", "爱民区", "西安区", "东宁", "林口", "绥芬河", "海林", "宁安", "穆棱" },
				{ "爱辉区", "嫩江", "逊克", "孙吴", "北安", "五大连池" }, { "北林区", "望奎", "兰西", "青冈", "庆安", "明水", "绥棱", "安达", "肇东", "海伦" }, { "加格达奇", "松岭", "新林区", "呼中区", "呼玛", "塔河", "漠河" } },
				{ { "黄浦", "卢湾", "徐汇", "长宁", "静安", "普陀", "闸北", "虹口", "杨浦", "闵行", "宝山", "嘉定", "浦东", "金山", "松江", "青浦", "南汇", "奉贤", "崇明" } },
				{ { "玄武", "白下", "秦淮", "建邺", "鼓楼", "下关", "浦口", "栖霞", "雨花台", "江宁", "六合", "溧水", "高淳" }, { "崇安", "南长区", "北塘区", "锡山", "惠山", "滨湖", "江阴", "宜兴" },
				{ "鼓楼", "云龙区", "九里区", "贾汪", "泉山", "丰县", "沛县", "铜山", "睢宁", "新沂", "邳州" }, { "天宁区", "钟楼区", "戚墅堰", "新北", "武进", "溧阳", "金坛" },
				{ "沧浪区", "平江", "金阊", "虎丘", "吴中", "相城", "常熟", "张家港", "昆山", "吴江", "太仓" }, { "崇川", "港闸", "海安", "如东", "启东", "如皋", "通州", "海门" },
				{ "连云区", "新浦", "海州", "赣榆", "东海", "灌云", "灌南" }, { "清河", "楚州", "淮阴", "清浦", "涟水", "洪泽", "盱眙", "金湖" }, { "亭湖区", "盐都区", "响水", "滨海", "阜宁", "射阳", "建湖", "东台", "大丰" },
				{ "广陵", "邗江", "维扬", "宝应", "仪征", "高邮", "江都" }, { "京口", "润州", "丹徒", "丹阳", "扬中", "句容" }, { "海陵", "高港", "兴化", "靖江", "泰兴", "姜堰" }, { "宿城", "宿豫", "沭阳", "泗阳", "泗洪" } },
				{ { "上城区", "下城区", "江干区", "拱墅", "西湖", "滨江", "萧山", "余杭", "桐庐", "淳安", "建德", "富阳", "临安" }, { "海曙", "江东区", "江北区", "北仑", "镇海", "鄞州", "象山", "宁海", "余姚", "慈溪", "奉化" },
				{ "鹿城", "龙湾区", "瓯海", "洞头", "永嘉", "平阳", "苍南", "文成", "泰顺", "瑞安", "乐清" }, { "南湖", "秀洲", "嘉善", "海盐", "海宁", "平湖", "桐乡" }, { "吴兴", "南浔", "德清", "长兴", "安吉" },
				{ "越城", "绍兴", "新昌", "诸暨", "上虞", "嵊州" }, { "婺城", "金东", "武义", "浦江", "磐安", "兰溪", "义乌", "东阳", "永康" }, { "柯城", "衢江", "常山", "开化", "龙游", "江山" }, { "定海", "普陀", "岱山", "嵊泗" },
				{ "椒江", "黄岩", "路桥", "玉环", "三门", "天台", "仙居", "温岭", "临海" }, { "莲都", "青田", "缙云", "遂昌", "松阳", "云和", "庆元", "景宁", "龙泉" } },
				{ { "瑶海", "庐阳", "蜀山", "包河", "长丰", "肥东", "肥西" }, { "镜湖", "弋江", "鸠江", "三山", "芜湖", "繁昌", "南陵" }, { "龙子湖", "蚌山", "禹会", "淮上", "怀远", "五河", "固镇" },
				{ "大通", "田家庵", "谢家集", "八公山", "潘集", "凤台" }, { "金家庄", "花山", "雨山", "当涂" }, { "杜集", "相山", "烈山", "濉溪" }, { "铜官山", "狮子山", "郊区", "铜陵" },
				{ "迎江区", "大观区", "宜秀", "怀宁", "枞阳", "潜山", "太湖", "宿松", "望江", "岳西", "桐城" }, { "屯溪", "黄山", "徽州", "歙县", "休宁", "黟县", "祁门" },
				{ "琅琊", "南谯", "来安", "全椒", "定远", "凤阳", "天长", "明光" }, { "颍州", "颍东", "颍泉", "临泉", "太和", "阜南", "颍上", "界首" }, { "埇桥区", "砀山", "萧县", "灵璧", "泗县" },
				{ "居巢区", "庐江", "无为", "含山", "和县" }, { "金安", "裕安", "寿县", "霍邱", "舒城", "金寨", "霍山" }, { "谯城", "涡阳", "蒙城", "利辛" }, { "贵池", "东至", "石台", "青阳" },
				{ "宣州", "郎溪", "广德", "泾县", "绩溪", "旌德", "宁国" } },
				{ { "鼓楼区", "台江", "仓山", "马尾区", "晋安", "闽侯", "连江", "罗源", "闽清", "永泰", "平潭", "福清", "长乐" }, { "思明", "海沧", "湖里", "集美", "同安", "翔安" }, { "城厢", "涵江", "荔城", "秀屿", "仙游" },
				{ "梅列", "三元", "明溪", "清流", "宁化", "大田", "尤溪", "沙县", "将乐", "泰宁", "建宁", "永安" }, { "鲤城", "丰泽", "洛江", "泉港", "惠安", "安溪", "永春", "德化", "金门", "石狮", "晋江", "南安" },
				{ "芗城", "龙文", "云霄", "漳浦", "诏安", "长泰", "东山", "南靖", "平和", "华安", "龙海" }, { "延平", "顺昌", "浦城", "光泽", "松溪", "政和", "邵武", "武夷山", "建瓯", "建阳" },
				{ "新罗", "长汀", "永定", "上杭", "武平", "连城", "漳平" }, { "蕉城", "霞浦", "古田", "屏南", "寿宁", "周宁", "柘荣", "福安", "福鼎" } },
				{ { "东湖", "西湖", "青云谱", "湾里", "青山湖", "南昌", "新建", "安义", "进贤" }, { "昌江", "珠山", "浮梁", "乐平" }, { "安源", "湘东", "莲花", "上栗", "芦溪" },
				{ "庐山", "浔阳", "九江", "武宁", "修水", "永修", "德安", "星子", "都昌", "湖口", "彭泽", "瑞昌" }, { "渝水", "分宜" }, { "月湖区", "余江", "贵溪" },
				{ "章贡", "赣县", "信丰", "大余", "上犹", "崇义", "安远", "龙南", "定南", "全南", "宁都", "于都", "兴国", "会昌", "寻乌", "石城", "瑞金", "南康" },
				{ "吉州", "青原", "吉安", "吉水", "峡江", "新干", "永丰", "泰和", "遂川", "万安", "安福", "永新", "井冈山" }, { "袁州", "奉新", "万载", "上高", "宜丰", "靖安", "铜鼓", "丰城", "樟树", "高安" },
				{ "临川", "南城", "黎川", "南丰", "崇仁", "乐安", "宜黄", "金溪", "资溪", "东乡", "广昌" }, { "信州", "上饶", "广丰", "玉山", "铅山", "横峰", "弋阳", "余干", "鄱阳", "万年", "婺源", "德兴" } },
				{ { "历下", "市中区", "槐荫", "天桥区", "历城区", "长清", "平阴", "济阳", "商河", "章丘" }, { "市南区", "市北区", "四方", "黄岛", "崂山", "李沧", "城阳", "胶州", "即墨", "平度", "胶南", "莱西" },
				{ "淄川", "张店", "博山", "临淄", "周村", "桓台", "高青", "沂源" }, { "市中区", "薛城", "峄城", "台儿庄", "山亭", "滕州" }, { "东营", "河口", "垦利", "利津", "广饶" },
				{ "芝罘", "福山", "牟平", "莱山", "长岛", "龙口", "莱阳", "莱州", "蓬莱", "招远", "栖霞", "海阳" }, { "潍城", "寒亭", "坊子", "奎文", "临朐", "昌乐", "青州", "诸城", "寿光", "安丘", "高密", "昌邑" },
				{ "市中区", "任城", "微山", "鱼台", "金乡", "嘉祥", "汶上", "泗水", "梁山", "曲阜", "兖州", "邹城" }, { "泰山区", "岱岳区", "宁阳", "东平", "新泰", "肥城" }, { "环翠区", "文登", "荣成", "乳山" },
				{ "东港区", "岚山", "五莲", "莒县" }, { "莱城", "钢城" }, { "兰山", "罗庄", "河东", "沂南", "郯城", "沂水", "苍山", "费县", "平邑", "莒南", "蒙阴", "临沭" },
				{ "德城区", "陵县", "宁津", "庆云", "临邑", "齐河", "平原", "夏津", "武城", "乐陵", "禹城" }, { "东昌府", "阳谷", "莘县", "茌平", "东阿", "冠县", "高唐", "临清" },
				{ "滨城区", "惠民", "阳信", "无棣", "沾化", "博兴", "邹平" }, { "牡丹区", "曹县", "单县", "成武", "巨野", "郓城", "鄄城", "定陶", "东明" } },
				{ { "中原", "二七", "管城", "金水", "上街", "惠济", "中牟", "巩义", "荥阳", "新密", "新郑", "登封" }, { "龙亭", "顺河", "鼓楼", "禹王台", "金明", "杞县", "通许", "尉氏", "开封", "兰考" },
				{ "老城区", "西工区", "瀍河", "涧西", "吉利", "洛龙", "孟津", "新安", "栾川", "嵩县", "汝阳", "宜阳", "洛宁", "伊川", "偃师" }, { "新华区", "卫东", "石龙", "湛河", "宝丰", "叶县", "鲁山", "郏县", "舞钢", "汝州" },
				{ "文峰区", "北关区", "殷都", "龙安", "安阳", "汤阴", "滑县", "内黄", "林州" }, { "鹤山", "山城", "淇滨", "浚县", "淇县" },
				{ "红旗区", "卫滨区", "凤泉", "牧野", "新乡", "获嘉", "原阳", "延津", "封丘", "长垣", "卫辉", "辉县" }, { "解放区", "中站区", "马村区", "山阳区", "修武", "博爱", "武陟", "温县", "沁阳", "孟州" },
				{ "华龙区", "清丰", "南乐", "范县", "台前", "濮阳" }, { "魏都", "许昌", "鄢陵", "襄城", "禹州", "长葛" }, { "源汇", "郾城", "召陵", "舞阳", "临颍" }, { "湖滨区", "渑池", "陕县", "卢氏", "义马", "灵宝" },
				{ "宛城区", "卧龙", "南召", "方城", "西峡", "镇平", "内乡", "淅川", "社旗", "唐河", "新野", "桐柏", "邓州" }, { "梁园", "睢阳", "民权", "睢县", "宁陵", "柘城", "虞城", "夏邑", "永城" },
				{ "浉河", "平桥", "罗山", "光山", "新县", "商城", "固始", "潢川", "淮滨县", "息县" }, { "川汇", "扶沟", "西华", "商水", "沈丘", "郸城", "淮阳", "太康", "鹿邑", "项城" },
				{ "驿城", "西平", "上蔡", "平舆", "正阳", "确山", "泌阳", "汝南", "遂平", "新蔡" } },
				{ { "江岸", "江汉", "硚口", "汉阳", "武昌", "青山", "洪山", "东西湖", "汉南", "蔡甸", "江夏", "黄陂", "新洲" }, { "黄石港", "西塞山", "下陆", "铁山", "阳新", "大冶" },
				{ "茅箭", "张湾", "郧县", "郧西", "竹山", "竹溪", "房县", "丹江口" }, { "西陵", "伍家岗", "点军", "猇亭", "夷陵", "远安", "兴山", "秭归", "长阳", "五峰", "宜都", "当阳", "枝江" },
				{ "襄城", "樊城", "襄阳", "南漳", "谷城", "保康", "老河口", "枣阳", "宜城" }, { "梁子湖", "华容", "鄂城" }, { "东宝区", "掇刀", "京山", "沙洋", "钟祥" }, { "孝南", "孝昌", "大悟", "云梦", "应城", "安陆", "汉川" },
				{ "沙市", "荆州", "公安", "监利", "江陵", "石首", "洪湖", "松滋" }, { "黄州", "团风", "红安", "罗田", "英山", "浠水", "蕲春", "黄梅", "麻城", "武穴" }, { "咸安", "嘉鱼", "通城", "崇阳", "通山", "赤壁" },
				{ "曾都", "广水" }, { "恩施", "利川", "建始", "巴东", "宣恩", "咸丰", "来凤", "鹤峰" } },
				{ { "芙蓉", "天心", "岳麓", "开福", "雨花", "长沙", "望城", "宁乡", "浏阳" }, { "荷塘", "芦淞", "石峰", "天元区", "株洲", "攸县", "茶陵", "炎陵", "醴陵" }, { "雨湖区", "岳塘", "湘潭", "湘乡", "韶山" },
				{ "珠晖", "雁峰", "石鼓", "蒸湘", "南岳", "衡阳", "衡南", "衡山", "衡东", "祁东", "耒阳", "常宁" }, { "双清", "大祥", "北塔", "邵东", "新邵", "邵阳", "隆回", "洞口", "绥宁", "新宁", "城步", "武冈" },
				{ "岳阳楼", "云溪", "君山", "岳阳", "华容", "湘阴", "平江", "汨罗", "临湘" }, { "武陵", "鼎城", "安乡", "汉寿", "澧县", "临澧", "桃源", "石门", "津市" }, { "永定", "武陵源", "慈利", "桑植" },
				{ "资阳", "赫山", "南县", "桃江", "安化", "沅江" }, { "北湖", "苏仙", "桂阳", "宜章", "永兴", "嘉禾", "临武", "汝城", "桂东", "安仁", "资兴" },
				{ "零陵区", "冷水滩", "祁阳", "东安", "双牌", "道县", "江永", "宁远", "蓝山", "新田", "江华" }, { "鹤城", "中方", "沅陵", "辰溪", "溆浦", "会同", "麻阳", "新晃", "芷江", "靖州", "通道", "洪江" },
				{ "娄星", "双峰", "新化", "冷水江", "涟源" }, { "吉首", "泸溪", "凤凰", "花垣", "保靖", "古丈", "永顺", "龙山" } },
				{ { "荔湾区", "越秀", "海珠区", "天河区", "白云区", "黄埔区", "番禺", "花都", "南沙区", "萝岗区", "增城", "从化" }, { "武江", "浈江", "曲江", "始兴", "仁化", "翁源", "乳源", "新丰", "乐昌", "南雄" },
				{ "罗湖", "福田", "南山", "宝安", "龙岗", "盐田" }, { "香洲", "斗门", "金湾" }, { "龙湖", "金平", "濠江", "潮阳", "潮南", "澄海", "南澳" }, { "禅城", "南海", "顺德", "三水", "高明" },
				{ "蓬江", "江海", "新会", "台山", "开平", "鹤山", "恩平" }, { "赤坎", "霞山", "坡头", "麻章", "遂溪", "徐闻", "廉江", "雷州", "吴川" }, { "茂南", "茂港", "电白", "高州", "化州", "信宜" },
				{ "端州", "鼎湖", "广宁", "怀集", "封开", "德庆", "高要", "四会" }, { "惠城区", "惠阳", "博罗", "惠东", "龙门" }, { "梅江区", "梅县", "大埔", "丰顺", "五华", "平远", "蕉岭", "兴宁" }, { "城区", "海丰", "陆河", "陆丰" },
				{ "源城", "紫金", "龙川", "连平", "和平", "东源" }, { "江城区", "阳西", "阳东", "阳春" }, { "清城区", "佛冈", "阳山", "连山", "连南", "清新", "英德", "连州" }, { "湘桥", "潮安", "饶平" },
				{ "榕城", "揭东", "揭西", "惠来", "普宁" }, { "云城", "新兴", "郁南", "云安", "罗定" } },
				{ { "兴宁", "青秀", "江南", "西乡塘", "良庆", "邕宁", "武鸣", "隆安", "马山", "上林", "宾阳", "横县" }, { "城中区", "鱼峰", "柳南", "柳北", "柳江", "柳城", "鹿寨", "融安", "融水", "三江" },
				{ "秀峰", "叠彩", "象山", "七星", "雁山", "阳朔", "临桂", "灵川", "全州", "兴安", "永福", "灌阳", "龙胜", "资源", "平乐", "荔浦", "恭城" }, { "万秀", "蝶山", "长洲", "苍梧", "藤县", "蒙山", "岑溪" },
				{ "海城区", "银海", "铁山港", "合浦" }, { "港口区", "防城", "上思", "东兴" }, { "钦南", "钦北", "灵山", "浦北" }, { "港北", "港南", "覃塘", "平南", "桂平" }, { "玉州", "容县", "陆川", "博白", "兴业", "北流" },
				{ "右江区", "田阳", "田东", "平果", "德保", "靖西", "那坡", "凌云", "乐业", "田林", "西林", "隆林" }, { "八步区", "昭平", "钟山", "富" },
				{ "金城江", "南丹", "天峨", "凤山", "东兰", "罗城", "环江", "巴马", "都安", "大化", "宜州" }, { "兴宾", "忻城", "象州", "武宣", "金秀", "合山" }, { "江洲", "扶绥", "宁明", "龙州", "大新", "天等", "凭祥" } },
				{ { "秀英", "龙华", "琼山", "美兰" } },
				{ { "万州", "涪陵", "渝中", "大渡口", "江北", "沙坪坝", "九龙坡", "南岸", "北碚", "万盛", "双桥", "渝北", "巴南", "黔江", "长寿", "江津", "合川", "永川", "南川" },
				{ "綦江", "潼南", "铜梁", "大足", "荣昌", "璧山", "梁平", "城口", "丰都", "垫江", "武隆", "忠县", "开县", "云阳", "奉节", "巫山", "巫溪", "石柱", "秀山", "酉阳", "彭水" } },
				{ { "锦江", "青羊区", "金牛区", "武侯", "成华", "龙泉驿", "青白江", "新都", "温江", "金堂", "双流", "郫县", "大邑", "蒲江", "新津", "都江堰", "彭州", "邛崃", "崇州" },
				{ "自流井区", "贡井区", "大安", "沿滩区", "荣县", "富顺" }, { "东区", "西区", "仁和", "米易", "盐边" }, { "江阳", "纳溪", "龙马潭", "泸", "合江", "叙永", "古蔺" }, { "旌阳", "中江", "罗江", "广汉", "什邡", "绵竹" },
				{ "涪城", "游仙", "三台", "盐亭", "安县", "梓潼", "北川", "平武", "江油" }, { "市中区", "元坝", "朝天", "旺苍", "青川", "剑阁", "苍溪" }, { "船山区", "安居区", "蓬溪", "射洪", "大英" },
				{ "市中区", "东兴区", "威远", "资中", "隆昌" }, { "市中区", "沙湾", "五通桥", "金口河", "犍为", "井研", "夹江", "沐川", "峨边", "马边", "峨眉山" }, { "顺庆", "高坪", "嘉陵", "南部", "营山", "蓬安", "仪陇", "西充", "阆中" },
				{ "东坡", "仁寿", "彭山", "洪雅", "丹棱", "青神" }, { "翠屏区", "宜宾", "南溪", "江安", "长宁", "高县", "珙县", "筠连", "兴文", "屏" }, { "广安", "岳池", "武胜", "邻水", "华蓥" },
				{ "通川", "达县", "宣汉", "开江", "大竹", "渠县", "万源" }, { "雨城", "名山", "荥经", "汉源", "石棉", "天全", "芦山", "宝兴" }, { "巴州区", "通江", "南江", "平昌" }, { "雁江", "安岳", "乐至", "简阳" },
				{ "汶川", "理县", "茂县", "松潘", "九寨沟", "金川", "小金", "黑水", "马尔康", "壤塘", "阿坝", "若尔盖", "红原" },
				{ "康定", "泸定", "丹巴", "九龙", "雅江", "道孚", "炉霍", "甘孜", "新龙", "德格", "白玉", "石渠", "色达", "理塘", "巴塘", "乡城", "稻城", "得荣" },
				{ "西昌", "木里", "盐源", "德昌", "会理", "会东", "宁南", "普格", "布拖", "金阳", "昭觉", "喜德", "冕宁", "越西", "甘洛", "美姑", "雷波" } },
				{ { "南明", "云岩", "花溪", "乌当", "白云", "小河", "开阳", "息烽", "修文", "清镇" }, { "钟山", "六枝特区", "水城", "盘县" },
				{ "红花岗", "汇川", "遵义", "桐梓", "绥阳", "正安", "道真", "务川", "凤冈", "湄潭", "余庆", "习水", "赤水", "仁怀" }, { "西秀", "平坝", "普定", "镇宁", "关岭", "紫云" },
				{ "铜仁", "江口", "玉屏", "石阡", "思南", "印江", "德江", "沿河", "松桃", "万山特区" }, { "兴义", "兴仁", "普安", "晴隆", "贞丰", "望谟", "册亨", "安龙" },
				{ "毕节", "大方", "黔西", "金沙", "织金", "纳雍", "威宁", "赫章" }, { "凯里", "黄平", "施秉", "三穗", "镇远", "岑巩", "天柱", "锦屏", "剑河", "台江", "黎平", "榕江", "从江", "雷山", "麻江", "丹寨" },
				{ "都匀", "福泉", "荔波", "贵定", "瓮安", "独山", "平塘", "罗甸", "长顺", "龙里", "惠水", "三都" } },
				{ { "五华", "盘龙", "官渡", "西山", "东川", "呈贡", "晋宁", "富民", "宜良", "石林", "嵩明", "禄劝", "寻甸", "安宁" }, { "麒麟区", "马龙", "陆良", "师宗", "罗平", "富源", "会泽", "沾益", "宣威" },
				{ "红塔区", "江川", "澄江", "通海", "华宁", "易门", "峨山", "新平", "元江" }, { "隆阳", "施甸", "腾冲", "龙陵", "昌宁" }, { "昭阳", "鲁甸", "巧家", "盐津", "大关", "永善", "绥江", "镇雄", "彝良", "威信", "水富" },
				{ "古城区", "玉龙", "永胜", "华坪", "宁蒗" }, { "思茅", "宁洱", "墨江", "景东", "景谷", "镇沅", "江城", "孟连", "澜沧", "西盟" }, { "临翔", "凤庆", "云县", "永德", "镇康", "双江", "耿马", "沧源" },
				{ "楚雄", "双柏", "牟定", "南华", "姚安", "大姚", "永仁", "元谋", "武定", "禄丰" }, { "个旧", "开远", "蒙自", "屏边", "建水", "石屏", "弥勒", "泸西", "元阳", "红河", "金平", "绿春", "河口" },
				{ "文山", "砚山", "西畴", "麻栗坡", "马关", "丘北", "广南", "富宁" }, { "景洪", "勐海", "勐腊" }, { "大理", "漾濞", "祥云", "宾川", "弥渡", "南涧", "巍山", "永平", "云龙", "洱源", "剑川", "鹤庆" },
				{ "瑞丽", "潞西", "梁河", "盈江", "陇川" }, { "泸水", "福贡", "贡山", "兰坪" }, { "香格里拉", "德钦", "维西" } },
				{ { "城关", "林周", "当雄", "尼木", "曲水", "堆龙德庆", "达孜", "墨竹工卡" }, { "昌都", "江达", "贡觉", "类乌齐", "丁青", "察雅", "八宿", "左贡", "芒康", "洛隆", "边坝" },
				{ "乃东", "扎囊", "贡嘎", "格桑", "琼结", "曲松", "措美", "洛扎", "加查", "隆子", "错那", "浪卡子" },
				{ "日喀则", "南木林", "江孜", "定日", "萨迦", "拉孜", "昂仁", "谢通门", "白朗", "仁布", "康马", "定结", "仲巴", "亚东", "吉隆", "聂拉木", "萨嘎", "岗巴" },
				{ "那曲", "嘉黎", "比如", "聂荣", "安多", "申扎", "索县", "班戈", "巴青", "尼玛" }, { "普兰", "札达", "噶尔", "日土", "革吉", "改则", "措勤" }, { "林芝", "工布江达", "米林", "墨脱", "波密", "察隅", "朗县" } },
				{ { "新城区", "碑林", "莲湖", "灞桥", "未央", "雁塔", "阎良", "临潼", "长安", "蓝田", "周至", "户县", "高陵" }, { "王益", "印台", "耀州", "宜君" },
				{ "渭滨", "金台", "陈仓", "凤翔", "岐山", "扶风", "眉县", "陇县", "千阳", "麟游", "凤县", "太白" }, { "秦都", "杨凌", "渭城", "三原", "泾阳", "乾县", "礼泉", "永寿", "彬县", "长武", "旬邑", "淳化", "武功", "兴平" },
				{ "临渭", "华县", "潼关", "大荔", "合阳", "澄城", "蒲城", "白水", "富平", "韩城", "华阴" }, { "宝塔区", "延长", "延川", "子长", "安塞", "志丹", "吴起", "甘泉", "富县", "洛川", "宜川", "黄龙", "黄陵" },
				{ "汉台", "南郑", "城固", "洋县", "西乡", "勉县", "宁强", "略阳", "镇巴", "留坝", "佛坪" }, { "榆阳", "神木", "府谷", "横山", "靖边", "定边", "绥德", "米脂", "佳县", "吴堡", "清涧", "子洲" },
				{ "汉滨", "汉阴", "石泉", "宁陕", "紫阳", "岚皋", "平利", "镇坪", "旬阳", "白河" }, { "商州", "洛南", "丹凤", "商南", "山阳", "镇安", "柞水" } },
				{ { "城关", "七里河", "西固", "安宁", "红古", "永登", "皋兰", "榆中" }, { "金川", "永昌" }, { "白银", "平川", "靖远", "会宁", "景泰" }, { "秦州", "麦积", "清水", "秦安", "甘谷", "武山", "张家川" },
				{ "凉州", "民勤", "古浪", "天祝" }, { "甘州", "肃南", "民乐", "临泽", "高台", "山丹" }, { "崆峒", "泾川", "灵台", "崇信", "华亭", "庄浪", "静宁" }, { "肃州", "金塔", "瓜州", "肃北", "阿克塞", "玉门", "敦煌" },
				{ "西峰", "庆城", "环县", "华池", "合水", "正宁", "宁县", "镇原" }, { "安定", "通渭", "陇西", "渭源", "临洮", "漳县", "岷县" }, { "武都", "成县", "文县", "宕昌", "康县", "西和", "礼县", "徽县", "两当" },
				{ "临夏", "临夏", "康乐", "永靖", "广河", "和政", "东乡", "积石山" }, { "合作", "临潭", "卓尼", "舟曲", "迭部", "玛曲", "碌曲", "夏河" } },
				{ { "城东区", "城中区", "城西区", "城北区", "大通", "湟中", "湟源" }, { "平安", "民和", "乐都", "互助", "化隆", "循化" }, { "门源", "祁连", "海晏", "刚察" }, { "同仁", "尖扎", "泽库", "河南" },
				{ "共和", "同德", "贵德", "兴海", "贵南" }, { "玛沁", "班玛", "甘德", "达日", "久治", "玛多" }, { "玉树", "杂多", "称多", "治多", "囊谦", "曲麻莱" }, { "格尔木", "德令哈", "乌兰", "都兰", "天峻" } },
				{ { "兴庆", "西夏", "金凤", "永宁", "贺兰", "灵武" }, { "大武口", "惠农", "平罗" }, { "利通", "盐池", "同心", "青铜峡" }, { "原州", "西吉", "隆德", "泾源", "彭阳" }, { "沙坡头", "中宁", "海原" } },
				{ { "天山", "沙依巴克", "新市区", "水磨沟区", "头屯河区", "达坂城区", "米东区", "乌鲁木齐" }, { "独山子", "克拉玛依", "白碱滩", "乌尔禾" }, { "吐鲁番", "鄯善", "托克逊" }, { "哈密", "巴里坤", "伊吾" },
				{ "昌吉", "阜康", "呼图壁", "玛纳斯", "奇台", "吉木萨尔", "木垒" }, { "博乐", "精河", "温泉" }, { "库尔勒", "轮台", "尉犁", "若羌", "且末", "焉耆", "和静", "和硕", "博湖" },
				{ "阿克苏", "温宿", "库车", "沙雅", "新和", "拜城", "乌什", "阿瓦提", "柯坪" }, { "阿图什", "阿克陶", "阿合奇", "乌恰" },
				{ "喀什", "疏附", "疏勒", "英吉沙", "泽普", "莎车", "叶城", "麦盖提", "岳普湖", "伽师", "巴楚", "塔什库尔干塔吉克" }, { "和田", "和田", "墨玉", "皮山", "洛浦", "策勒", "于田", "民丰" },
				{ "伊宁", "奎屯", "伊宁", "察布查尔锡伯", "霍城", "巩留", "新源", "昭苏", "特克斯", "尼勒克" }, { "塔城", "乌苏", "额敏", "沙湾", "托里", "裕民", "和布克赛尔" },
				{ "阿勒泰", "布尔津", "富蕴", "福海", "哈巴河", "青河", "吉木乃" }, { "石河子", "阿拉尔", "图木舒克", "五家渠" } } };
	}
	

	public class Person {
		
		private String name;
		private String number;
		public Location address=new Location();
		public void Setname(String s) {name=s;}
		public void Setnumber(String s) {number=s;}
		public String display() {
			StringBuffer result=new StringBuffer();
			result.append("{"+""+"\"姓名\":\""+name+"\","+""+"\"电话号码\":\""+number+"\",");
			String result2= address.displayFIVE();
			return result.toString()+result2;
		}
	}
	
	
	public static String readFile(String x) {
        String pathname = x;
        String result="[";
        try (InputStreamReader ipr = new InputStreamReader(new FileInputStream(pathname),Charset.forName("UTF-8"));
        		BufferedReader br = new BufferedReader(ipr); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            int i=0;
            while ((line = br.readLine()) != null) {
            	if(i!=0)
            		result=result+",";
            	i++;
            	result=result+workout(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        result=result+"]";
        return result;
    }
	public static void writeFile(String x,String x2) {
        try {
            File writeName = new File(x2);
            writeName.createNewFile(); 
            try (FileOutputStream writerStream = new FileOutputStream(writeName); 
            		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8")); ) 
            {
            	//System.out.println(JSON.parse(x));
            	//System.out.println(x);
                writer.write(x);
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	public static String workout(String str) {
		String result="";
		String[] re=str.split("!|,",3);
		Main s=new Main();
		Person someone=s.new Person();
		someone.Setname(re[1]);
		int i=0,j=0;
		for(i=0;i<re[2].length();i++)
		{
			int z=i;
			char a=re[2].charAt(i);
			if(a>'0'&&a<='9'){
				for (;z<=(i+11); z++){
					char b=re[2].charAt(z);
					if(b>='0'&&b<='9')
						;
					else
						break;
				}
			}
			if(z==(i+11))
			{
				someone.Setnumber(re[2].substring(i,i+11));
				re[2]=re[2].replaceFirst(re[2].substring(i,i+11),"");
				break;
			}
		}
		for(i=0;i<AddressLibrary.provinces.length;i++) 
		{
			String Province=AddressLibrary.provinces[i];
			int result1=re[2].indexOf(Province);
			if(result1!=-1)
			{
				for(j=0;j<AddressLibrary.citys[i].length;j++) {
					String city=AddressLibrary.citys[i][j];
					int result2=re[2].indexOf(city);
					if(result2!=-1)
					{
						someone.address.SetProVinceCity(Province,city,i);
						re[2]=re[2].replaceFirst(Province,"");
						if(re[2].charAt(0)=='省')
							re[2]=re[2].replaceFirst("省","");
						re[2]=re[2].replaceFirst(city,"");
						if(re[2].charAt(0)=='市')
							re[2]=re[2].replaceFirst("市","");
						break;
					}
					else if(j==AddressLibrary.citys[i].length-1 ) {
						someone.address.SetProVinceCity(Province,"",i);
						re[2]=re[2].replaceFirst(Province,"");
						if(re[2].charAt(0)=='省')
						re[2]=re[2].replaceFirst("省","");
					}
				}
			}
		}
		
		if(re[0].charAt(0)=='1'){
			someone.address.SetHard("1");
			someone.address.SetFive(re[2]);
		}
		else if(re[0].charAt(0)=='2'){
			someone.address.SetHard("2");
			someone.address.SetAll(re[2],i,j);
		}
		else {
			someone.address.SetHard("3");
			someone.address.SetAllThree(re[2],i,j);
		}
		result=result+someone.display();
		return result;
	}
	
	public class Location{
		private String province;
		private String city;
		private String county;
		private String town;
		private String block;
		private String door;
		private String detail;
		private String hard;
		public Location() { province="";city="";county="";town="";block="";door="";detail="";}
		public void SetHard(String ha) {this.hard=ha;}//设置难度
		public void SetProVinceCity(String s1,String s2,int num) {
			if(num==0||num==8||num==1||num==20||num==21||num==33||num==34||num==4||num==30||num==29||num==19||num==25)
				this.province=s1;
			else
				this.province=s1+"省";
			this.city=s2+"市";
			if(city.length()==1)
				this.city=city.replace("市", "");
		}//设置省和市；
		public String displayFIVE() {
			StringBuffer s=new StringBuffer();
			if(county=="区")
				county=county.replaceFirst("区", "");
			if(hard=="1")
			{
				s.append(""+"\"地区\":"+"["+""+"\""+province+"\","+""+"\""+city+"\","+""+"\""+county+"\","+""+"\""+town+"\","+""+"\""+detail+"\""+""+"]"+"}");
			}
			else
			{
				s.append(""+"\"地区\":"+"["+""+"\""+province+"\","+""+"\""+city+"\","+""+"\""+county+"\","+""+"\""+town+"\","+""+"\""+block+"\","+""+"\""+door+"\","+""+"\""+detail+"\""+"\t\t"+"]"+"}");
			}
			return s.toString();
		}//输出
		public void SetFive(String s) {
			for(int i=0;i<s.length();i++)
			{
				if(s.charAt(i)=='县'||s.charAt(i)=='区'||s.charAt(i)=='市'||s.charAt(i)=='州'){
					if(county=="") {
						county=s.substring(0,i+1);
						s=s.replaceFirst(county,"");
						break;
					}
				}
			}
			for(int i=0;i<s.length();i++) {
				if(s.charAt(i)=='街'||s.charAt(i)=='镇'||s.charAt(i)=='乡') {
					if(s.charAt(i+1)=='道'||s.charAt(i+1)=='镇') {
						town=s.substring(0,i+2);
						s=s.replaceFirst(town, "");
						break;
					}
					else {
						town=s.substring(0,i+1);
						s=s.replaceFirst(town, "");
						break;
					}
				}
			}
			detail=s.substring(0,s.length()-1);
		}
		
		
		public int SetAll(String s,int x,int y) {
			int z=-1;
			String str;
			if(x!=AddressLibrary.provinces.length&&y!=AddressLibrary.citys[x].length)
			{
				for(int i=0;i<AddressLibrary.countys[x][y].length;i++) {
					str=AddressLibrary.countys[x][y][i];
					int result=s.indexOf(str);
					if(result!=-1)
					{
						county=str;
						s=s.replaceFirst(str, "");
						z=i;
						break;
					}
							
				}
				if(s.charAt(0)=='县')
				{
					s=s.replaceFirst("县", "");
					county=county+"县";
				}
				else if(s.charAt(0)=='区')
				{
					s=s.replaceFirst("区", "");
					county=county+"区";
				}
			}
			else
			{
				for(int i=0;i<s.length();i++){//县级
					if(s.charAt(i)=='县'||s.charAt(i)=='区'){
						if(county=="") {
							county=s.substring(0,i+1);
							s=s.replaceFirst(county,"");
							break;
						}
					}
				}
			}
			for(int i=0;i<s.length();i++) {//乡镇级
				if(s.charAt(i)=='街'||s.charAt(i)=='镇'||s.charAt(i)=='乡') {
					if(s.charAt(i+1)=='道'||s.charAt(i+1)=='镇') {
						town=s.substring(0,i+2);
						s=s.replaceFirst(town, "");
						break;
					}
					else {
						town=s.substring(0,i+1);
						s=s.replaceFirst(town, "");
						break;
					}
				}
			}
			for(int i=0;i<s.length();i++) {//路名
				if(s.charAt(i)=='路'||s.charAt(i)=='街') {
					block=s.substring(0,i+1);
					s=s.replaceFirst(block, "");
					break;
				}
			}
			for(int i=0;i<s.length();i++)
				if(s.charAt(i)=='号') {
					door=s.substring(0,i+1);
					s=s.replaceFirst(door, "");
					break;
				}
			if(s.length()!=0)
			detail=s.substring(0,s.length()-1);
			return z;
		}
		
		public void SetAllThree(String s,int x,int y) {
			int z=SetAll(s, x, y);
			if(province==""&&city!="")
				province=AddressLibrary.provinces[x];
			else if(city=="") {
				if(province!=""&&county!="")
				{
					for(int i=0;;i++)
					{
						if(AddressLibrary.countys[x][i][y]!=null)
						{
							if(AddressLibrary.countys[x][i][y]==county)
							{
								city=AddressLibrary.citys[x][i];
								break;
							}
						}
						if(i==30)
							break;
					}
				}
			}	
		}
	}

	
	public static void main(String[] args) {
		String str=readFile(args[0]);
		writeFile(str, args[1]);
		return;
	}
}

