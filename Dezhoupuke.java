
public class ComparePlayer1ToPlayer2 {
	
	public static int compare(Player player1, Player player2)
			throws InterruptedException{
		
		Collections.sort(player1.getPorkerList());
		Collections.sort(player2.getPorkerList());
		
		CountDownLatch lock = new CountDownLatch(2);
		new Thread(new WhichPorkerType(player1,lock)).start();
		new Thread(new WhichPorkerType(player2,lock)).start();
		
		lock.await();
		int porkerType1 = player1.getPorkerType().getTypeCode();
		int porkerType2 = player2.getPorkerType().getTypeCode();
		if(porkerType1!=porkerType2){
			return porkerType1>porkerType2?1:porkerType1==porkerType2?0:-1;
		}
		else {
			PorkerType porkerType =  player1.getPorkerType();
			int result = compare(player1, player2,porkerType);
			return result ;
		}
	}
	
	private static int compare(Player player1, Player player2, PorkerType porkerType) {
		List<Porker> porkerList1 = player1.getPorkerList();
		List<Porker> porkerList2 = player2.getPorkerList();
		String typeName = porkerType.getTypeName();
		if(typeName.equals("高牌")){
			for(int i=0;i<porkerList1.size();i++){
				Porker porker1 = porkerList1.get(i);
				Porker porker2 = porkerList2.get(i);
				if(porker1.getNum()==porker2.getNum()){
					continue;
				}
				if(porker1.getNum()>porker2.getNum()){
					return 1;
				}else{
					return -1;
				}
			}
			return 0;
		}
		
		if(typeName.equals("四条")||
				typeName.equals("满堂红")||
				typeName.equals("三条")||
				typeName.equals("两对")||
				typeName.equals("一对")){
			List<Porker> sortedList1 = sortByPairs(porkerList1);
			player1.setPorkerList(sortedList1);
			List<Porker> sortedList2 = sortByPairs(porkerList2);
			player2.setPorkerList(sortedList2);
			
			for(int i=0;i<sortedList1.size();i++){
				Porker p1 = sortedList1.get(i);
				Porker p2 = sortedList2.get(i);
				if(p1.getNum()==p2.getNum()){
					continue;
				}
				return p1.getNum()>p2.getNum()?1:-1;
			}
			return 0;
		}
		
		if(typeName.equals("同花")){
			for(int i=porkerList1.size()-1;i>-1;i--){
				int num1 = porkerList1.get(i).getNum();
				int num2 = porkerList2.get(i).getNum();
				if(num1==num2){
					continue;
				}
				return num1>num2?1:-1;
			}
			return 0;
		}
		if(typeName.equals("顺子")
				||typeName.equals("同花顺")){
			int totalNum1 = 0;
			int totalNum2 = 0;
			
			for(int i=0;i<porkerList1.size();i++){
				totalNum1+=porkerList1.get(i).getNum();
				totalNum2+=porkerList2.get(i).getNum();
			}
			if(totalNum1==totalNum2){
				return 0;
			}
			if(totalNum1==28){
				return -1;
			}
			if(totalNum2==28){
				return 1;
			}
			return totalNum1>totalNum2?1:-1;
		}
		if(typeName.equals("同花大顺")){
			return 0;
		}
		
		
		return 0;
	}
	
	private static List<Porker> sortByPairs(List<Porker> porkerList) {
		Map<Integer,Integer> map = new HashMap<>();
		for(Porker porker:porkerList){
			Integer num = porker.getNum();
			Integer count = map.get(num);
			if(count!=null){
				count++;
				map.put(num, count);
			}else{
				map.put(num, 1);
			}
		}
		List<Porker> sortedList = new ArrayList<>();
		Map<Integer,Integer> sortedMap = MapUtils.sortMapByValue(map);
		for(Map.Entry<Integer,Integer> e :  sortedMap.entrySet()){
			Integer num = e.getKey();
			for(Porker p :porkerList){
				if(p.getNum()==num){
					sortedList.add(p);
				}
			}
			
		}
		return sortedList;
	}
	
	public static void main(String[] args) {
		List<Porker> porkerList= new ArrayList<>();
 
		List<Porker> porkerList1 = sortByPairs(porkerList);
		for(Porker p :porkerList1){
			System.out.println(p);
		}
	}
}



public class MainMethod {
	
	public static void main(String[] args) throws Exception {
		Player player1 = new Player();
		List<Porker> porkerList1 = new ArrayList<>();

		player1.setPorkerList(porkerList1);
		
		
		Player player2 = new Player();
		List<Porker> porkerList2 = new ArrayList<>();
		
		player2.setPorkerList(porkerList2);
		
		int i = ComparePlayer1ToPlayer2.compare(player1,player2);
		if(i>0){
			System.out.println("Black wins");
		}else if(i==0){
			System.out.println("Tie");
		}else{
			System.out.println("White wins");
		}
		
		System.out.println("Black："+player1.toString());
		System.out.println("White："+player2.toString());
	}
}