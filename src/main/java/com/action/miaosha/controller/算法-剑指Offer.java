3.
public boolean duplicate(int[] nums, int length, int[] duplication) {
	if (nums == null || length == 0) {
		return false;
	}
	for (int i = 0; i < length; i++) {
		while (i != nums[i]) {
			if (nums[i] == nums[nums[i]]) {
				duplication[0] = nums[i];
				return true;
			}
			swap(nums, i, nums[i]);
		}
	}
	return false;
}
private void swap(int[] nums, int i, int j) {
	int t = nums[i];
	nums[i] = nums[j];
	nums[j] = t;
}

4.
public boolean find(int target, int[][] matrix) {
	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
		return false;
	}
	int rows = matrix.length, cols = matrix[0].length;
	int r = 0, c = cols - 1;
	while (r <= rows - 1 && c >= 0) {
		if (target == matrix[r][c]) {
			return true;
		}
		else if (target < matrix[r][c]) {
			r++;
		} else 
			c--;
	}
	return false;
}

5.
public String replaceSpace(StringBuffer str) {
	int p1 = str.length() - 1;
	for (int i = 0; i <= p1; i++) {
		if (str.charAt(i) == ' ') {
			str.append("  ");
		}
	}
	int p2 = str.length() - 1;
	while (p1 >= 0 && p2 > p1) {
		char c = str.charAt(p1--);
		if (c == ' ') {
			str.setCharAt(p2--, '0');
			str.setCharAt(p2--, '2');
			str.setCharAt(p2--, '%');
		} else {
			str.setCharAt(p2--, c);
		}
	}
	return str.toString();
}

6.
public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
	ArrayList<Integer> ret = new ArrayList<>();
	if (listNode != null) {
		ret.addAll(printListFromTailToHead(listNode.next));
		ret.add(listNode.val);
	}
	return ret;
}
public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
	ListNode head = new ListNode(-1);
	while(listNode != null) {
		ListNode next = listNode.next;
		listNode.next = head.next;
		head.next = listNode;
		listNode = next;
	}
	ArrayList<Integer> ret = new ArrayList<>();
	head = head.next;
	while (head != null) {
		ret.add(head.val);
		head = head.next;
	}
	return ret;
}

7.
private Map<Integer, Integer> indexForInOrders = new HashMap<>();
public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
	for (int i = 0; i < in.length; i++) {
		indexForInOrders.put(in[i], i);
	}
	return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
}
private TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL) {
	if (preL > preR) {
		return null;
	}
	TreeNode root = new TreeNode(pre[preL]);
	int inIndex = indexForInOrders.get(root.val);
	int leftTreeSize = inIndex - inL;
	root.left = reConstructBinaryTree(pre, preL + 1, preL + leftTreeSize, inL);
	root.right = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inL  + leftTreeSize + 1);
    return root;
}

8.
public TreeLinkNode GetNext(TreeLinkNode pNode) {
	if (pNode.right != null) {
		TreeNode node = pNode.right;
		while (node.left != null) {
			node = node.left;
		}
		return node;
	} else {
		while (pNode.next != null) {
			TreeLinkNode parent = pNode.next;
			if (parent.left == pNode) {
				return parent;
			}
			pNode = pNode.next;
		}
	}
	return null;
}

9.
Stack<Integer> in = new Stack<Integer>();
Stack<Integer> out = new Stack<Integer>();
public void push(int node) {
	in.push(node);
}
public int pop() throws Exception {
	if (out.isEmpty()) {
		if (!in.isEmpty()) {
			out.push(in.pop());
		}
	}
	if (out.isEmpty()) {
		throw new Exception("queue is empty");
	}
	return out.pop();
}

11.
public int minNumberInRotateArray(int[] nums) {
	if (nums.length == 0) {
		return 0;
	}
	int l = 0, h = nums.length - 1;
	while (l < h) {
		int m = l + (h - l) / 2;
		if (nums[m] <= nums[h]) {
			h = m;
		} else
			l = m + 1;
	}
	return nums[l];
}
public int minNumberInRotateArray(int[] nums) {
	if (nums.length == 0) {
		return 0;
	}
	int l = 0, h = nums.length - 1;
	while (l < h) {
		int m = l + (h - l) / 2;
		if (nums[l] == nums[m] == nums[h]) {
			return minNumber(nums, l, h);
		} else if (nums[m] <= nums[h]) {
			h = m;
		} else
			l = m + 1;
	}
	return nums[l];
}
private int minNumber(int[] nums, int l, int h) {
	for (int i = l; i < h; i++) {
		if (nums[i] > nums[i + 1) {
			return nums[i + 1];
		}
	}
	return nums[l];
}

14.
public int integerBreak(int n) {
	if (n < 2) {
		return 0;
	}
	if (n == 2) {
		return 1;
	}
	if (n == 3) {
		return 2;
	}
	int timesOf3 = n / 3;
	if (n - 3 * timesOf3 == 1) {
		timesOf3--;
	}
	int timesOf2 = (n - timesOf3 * 3) / 2;
	return (int) (Math.pow(3, timesOf3)) * (int) (Math.pow(2, timesOf2));
}
public int integerBreak(int n) {
	int[] dp = new int[n + 1];
	dp[1] = 1;
	for (int i = 2; i <= n; i++) {
		for (int j = 1; j < i; j++) {
			dp[i] = Math.max(dp[i], Math.max(j * (i - j), dp[j] * dp[i - j]));
		}
	}
	return dp[n];
}

15.
public int NumberOf1(int n) {
	int cnt = 0;
	while (n != 0) {
		n &= (n - 1);
		cnt++;
	}
	return cnt;
}

16
public double Power(double base, int exponent) {
	if (exponent == 0) {
		return 1;
	}
	if (exponent == 1) {
		return base;
	}
	boolean isNegative = false;
	if (exponent < 0) {
		exponent = -exponent;
		isNegative = true;
	}
	double pow = Power(base * base, exponent / 2);
	if (exponent % 2) {
		pow *= base;
	}
	return isNegative ? 1 / pow : pow;
}

17
public void print1ToMaxOfNDigits(int n) {
	if (n <= 0) {
		return;
	}
	char[] number = new char[n];
	print1ToMaxOfNDigits(number, 0);
}
private void print1ToMaxOfNDigits(char[] number, int digit) {
	if (digit == number.length) {
		printNumber(number);
		return;
	}
	for (int i = 0; i < 10; i++) {
		number[digit] = (char) (i + '0');
		print1ToMaxOfNDigits(number, digit + 1);
	}
}

18.1.
public ListNode deleteNode(ListNode head, ListNode tobeDelete) {
	if (head == null || tobeDelete == null) {
		return null;
	}
	if (tobeDelete.next != null) {
		ListNode next = tobeDelete.next;
		tobeDelete.val = next.val;
		tobeDelete.next = next.next;
	} else {
		if (head == tobeDelete) {
			head = null;
		} else {
			ListNode cur = head;
			while (cur.next != tobeDelete) {
				cur = cur.next;
			}
			cur.next = null;
		}
	}
	return head;
}

18.2
public ListNode deleteDuplication(ListNode pHead) {
	if (pHead == null || pHead.next == null) {
		return pHead;
	}
	ListNode next = pHead.next;
	if (pHead.val == next.val) {
		while (next != null && pHead.val == next.val) {
			next = next.val;
		}
		return deleteDuplication(next);
	} else {
		pHead.next = deleteDuplication(pHead.next);
		return pHead;
	}
}

21.
public void reOrderArray(int[] nums) {
	int oddCnt = 0;
	for (int x : nums) {
		if (!isEven(x)) {
			oddCnt++;
		}
	}
	int[] copy = nums.clone();
	int i = 0, j = oddCnt;
	for (int num : copy) {
		if (num % 2 == 1) {
			nums[i++] = num;
		} else {
			nums[j++] = num;
		}
	}
}
private boolean isEven(int x) {
	return x % 2 == 0;
}

public void reOrderArray(int[] nums) {
	int N = nums.length;
	for (int i = N - 1; i > 0; i--) {
		for (int j = 0; j < i; j++) {
			if (isEven(nums[j] && !isEven(nums[j + 1])) {
				swap(nums, j, j + 1);
			}
		}
	}
}

22.
public ListNode findKthToTail(ListNode head, int k) {
	if (head == null) {
		return null;
	}
	ListNode p1 = head;
	while (p1 != null && k-- > 0) {
		p1 = p1.next;
	}
	if (k > 0) {
		return null;
	}
	ListNode p2 = head;
	while (p1 != null) {
		p1 = p1.next;
		p2 = p2.next;
	}
	return p2;
}

23.
public ListNode EntryNodeOfLoop(ListNode pHead) {
	if (pHead == null || pHead.next == null) {
		return null;
	}
	ListNode slow = pHead, fast = pHead;
	do{
		fast = fast.next.next;
		slow = slow.next;
	}
	while (slow != fast);
	fast = pHead;
	while (slow != fast) {
		slow = slow.next;
		fast = fast.next;
	}
	return slow;
}

26.
public boolean hasSubtree(TreeNode root1, TreeNode root2) {
	if (root1 == null || root2 == null) {
		return false;
	}
	return isSubTreeWithRoot(root1, root2) || hasSubtree(root1.left, root2) || hasSubtree(root1.right, root2);
}
private isSubTreeWithRoot(TreeNode root1, TreeNode root2) {
	if (root2 == null) {
		return true;
	}
	if (root1 == null) {
		return false;
	}
	if (root1.val != root2.val) {
		return false;
	}
	return isSubTreeWithRoot(root1.left, root2.left) && isSubTreeWithRoot(root1.right, root2.right);
}

27.
public void mirror(TreeNode root) {
	if (root == null) {
		return;
	}
	swap(root);
	mirror(root.left);
	mirror(root.right);
}
private void swap(TreeNode root) {
	TreeNode t = root.left;
	root.left = root.right;
	root.right = t;
}

28.
boolean isSymmetrical(TreeNode pRoot) {
	if (pRoot == null) {
		return true;
	}
	return isSymmetrical(pRoot.left, pRoot.right);
}
boolean isSymmetrical(TreeNode t1, TreeNode t2) {
	if (t1 == null && t2 == null) {
		return true;
	}
	if (t1 == null || t2 == null) {
		return false;
	}
	if (t1.val != t2.val) {
		return false;
	}
	return isSymmetrical(t1.left, t2.right) && isSymmetrical(t1.right, t2.left);
}

29.
public ArrayList<Integer> printMatrix(int[][] matrix) {
	ArrayList<Integer> ret = new ArrayList<>();
	int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;
	while (r1 <= r2 && c1 <= c2) {
		for (int i = c1; i <= c2; i++) {
			ret.add(matrix[r1][i]);
		}
		for (int i = r1 + 1; i <= r2; i++) {
			ret.add(matrix[i][c2]);
		}
		if (r1 != r2) {
			for (int i = c2 - 1; i >= c1; i++) {
				ret.add(matrix[r2][i]);
			}
		}
		if (c1 != c2) {
			for (int i = r2 - 1; i > r1; i--) {
				ret.add(matrix[i][c1]);
			}
		}
	}
	return ret;
}

31.
public boolean isPopOrder(int[] pushSequence, int[] popSequence) {
	int n = pushSequence.length;
	Stack<Integer> stack = new Stack<>();
	for (int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++) {
		stack.push(pushSequence[pushIndex]);
		while (popIndex < n && !stack.isEmpty() && stack.peek() == popSequence[popIndex]) {
			stack.pop();
			popIndex++;
		}
	}
	return stack.isEmpty();
}

32.1
public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
	Queue<TreeNode> queue = new LinkedList<>();
	ArrayList<Integer> ret = new ArrayList<>();
	queue.add(root);
	while (!queue.isEmpty()) {
		int cnt = queue.size();
		while (cnt-- > 0) {
			TreeNode t = queue.poll();
			if (t == null) {
				continue;
			}
			ret.add(t.val);
			queue.add(t.left);
			queue.add(t.right);
		}
	}
	return ret;
}

33
public boolean verifySequenceOfBST(int[] sequence) {
	if (sequence == null | sequence.length == 0) {
		return false;
	}
	return verify(sequence, 0, sequence.length - 1);
}
private boolean verify(int[] sequence, int first, int last) {
	if (last - first <= 1) {
		return true;
	}
	int rootVal = sequence[last];
	int cutIndex = first;
	while (cutIndex < last && sequence[cutIndex] <= rootVal) {
		cutIndex++;
	}
	for (int i = cutIndex; i < last; i++) {
		if (sequence[i] < rootVal) {
			return false;
		}
	}
	return verify(sequence, first, cutIndex - 1) && verify(sequence, cutIndex, last - 1);
}

34.
private ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
	backtracking(root, target, new ArrayList<>());
	return ret;
}
private void backtracking(TreeNode node, int target, ArrayList<Integer> path) {
	if (root == null) {
		return;
	}
	path.add(node.val);
	target -= node.val;
	if (target == 0 && node.left == null && node.right == null) {
		ret.add(new ArrayList<>(path));
	} else {
		backtracking(node.left, target, path);
		backtracking(node.right, target, path);
	}
	path.remove(path.size() - 1);
}

36.
private TreeNode pre = null;
private TreeNode head == null;

public TreeNode convert(TreeNode root) {
	inOrder(root);
	return head;
}
private void inOrder(TreeNode node) {
	if (node == null) {
		return;
	}
	inOrder(node.left);
	node.left = pre;
	if (pre != null) {
		pre.right = node;
	}
	pre = node;
	if (head == null) {
		head = node;
	}
	inOrder(node.right);
}

37.
private String deserializeStr;

public String Serialize(TreeNode root) {
	if (root == null) {
		return "#";
	}
	return root.val + " " + Serialize(root.left) + " " + Serialize(root.right);
}
public TreeNode Deserialize(String str) {
	deserializeStr = str;
	return Deserialize();
}
private TreeNode Deserialize() {
	if (deserializeStr.length() == 0) {
		return null;
	}
	int index = deserializeStr.indexOf(" ");
	String node = index == -1 ? deserializeStr : deserializeStr.substring(0, index);
	deserializeStr = index == -1 ? "" : deserializeStr.substring(index + 1);
	if (node.equals("#") {
		return null;
	}
	int val = Integer.valueOf(node);
	TreeNode t = new TreeNode(val);
	t.left = Deserialize();
	t.right = Deserialize();
	return t;
}

38.
private ArrayList<String> ret = new ArrayList<>();
public ArrayList<String> permutation(String str) {
	if (str.length == 0) {
		return ret;
	}
	char[] chars = str.toCharArray();
	Arrays.sort(chars);
	backtracking(chars, new boolean[chars.length], new StringBuilder());
	return ret;
}
private void backtracking(char[] chars, boolean[] hasUsed, StringBuilder s) {
	if (s.length() == chars.length) {
		ret.add(s.toString());
		return;
	}
	for (int i = 0; i < chars.length; i++) {
		if (hasUsed[i]) {
			continue;
		}
		if (i != 0 && i < chars[i] == chars[i - 1] && !hasUsed[i - 1) {
			continue;
		}
		hasUsed[i] = true;
		s.append(chars[i]);
		backtracking(chars, hasUsed, s);
		s.deleteCharAt(s.length() - 1);
		hasUsed[i] = false;
	}
}

39.
public int MoreThanHalfNum_Solution(int[] nums) {
	int majority = nums[0];
	for (int i = 1, cnt = 1; i < nums.length; i++) {
		cnt = nums[i] == majority ? cnt + 1 : cnt - 1;
		if (cnt == 0) {
			majority = nums[i];
			cnt = 1;
		}
	}
	int cnt = 0;
	for (int val : nums) {
		if (val == majority) {
			cnt++;
		}
	}
	return cnt > nums.length / 2 ? majority : 0;
}

40.
public ArrayList<Integer> GetLeastNumbers_Solution(int[] nums, int k) {
	ArrayList<Integer> ret = new ArrayList<>();
	if (k > nums.length || k <= 0) {
		return ret;
	}
	findKthSmallest(nums, k - 1);
	for (int i = 0; i < k; i++) {
		ret.add(nums[i]);
	}
	return ret;
}
public void findKthSmallest(int[] nums, int k) {
	int l = 0, h = nums.length - 1;
	while (l < h) {
		int j = patition(nums, l, h);
		if (j == k) {
			break;
		}
		if (j > k) {
			h = j - 1;
		}
		else
			l = j + 1;
	}
}
private int patition(int[] nums, int l, int h) {
	int p = nums[l];
	int i = l, j = h + 1;
	while (true) {
		while (i != h && nums[++i] < p);
		while (j != i && nums[--j] > p);
		if (i >= j) {
			break;
		}
		swap(nums, i, j);
	}
	swap(nums, l, j);
	return j;
}
public ArrayList<Integer> GetLeastNumbers_Solution(int[] nums, int k) {
	if (k > nums.length || k <= 0) {
		return new ArrayList<>();
	}
	PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> (o2 - o1));
	for (int num : nums) {
		maxHeap.add(num);
		if (maxHeap.size() > k) {
			maxHeap.poll();
		}
	}
	return new ArrayList<>(maxHeap);
}

41.2
private int[] cnts = new int[256];
private Queue<Character> queue = new LinkedList<>();

public void Insert(char ch) {
	cnts[ch]++;
	queue.add(ch);
	while (!queue.isEmpty() && cnts[queue.peek()] > 1) {
		queue.poll();
	}
}
public char firstAppearingOnce() {
	return queue.isEmpty() ? '#' : queue.peek();
}

45.
public String PrintMinNumber(int[] numbers) {
	if (number == null || numbers.length == 0) {
		return "";
	}
	int n = numbers.length;
	String[] nums = new String[n];
	for (int i = 0; i < n; i++) {
		nums[i] = numbers[i] + "";
	}
	Arrays.sort(nums, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
	String ret = "";
	for (String str : nums) {
		ret += str;
	}
	return ret;
}

46.
public int numDecodings(String s) {
	if (s == null || s.length() == 0) {
		return 0;
	}
	int n = s.length();
	int[] dp = new int[n + 1];
	dp[0] = 1;
	dp[1] = s.charAt(0) == '0' ? 0 : 1;
	for (int i = 2; i <= n; i++) {
		int one = Integer.valueOf(s.substring(i - 1, i));
		if (one != 0) {
			dp[i] += dp[i - 1];
		}
		if (s.charAt(i - 2) == '0') {
			continue;
		}
		int two = Integer.valueOf(s.substring(i - 2, i));
		if (two <= 26) {
			dp[i] += dp[i - 2];
		}
	}
	return dp[n];
}

47.
public int getMost(int[][] values) {
	if (values == null || values.length == 0 || values[0].length == 0) {
		return 0;
	}
	int n = values[0].length;
	int[] dp = new int[n];
	for (int[] value : values) {
		dp[0] += value[0];
		for (int i = 1; i < n; i++) {
			dp[i] = Math.max(dp[i], dp[i - 1]) + value[i];
		}
	}
	return dp[n - 1];
}

48.
public int longestSubStringWithoutDuplication(String str) {
	int curLen = 0;
	int maxLen = 0;
	int[] preIndexs = new int[26];
	Arrays.fill(preIndexs, -1);
	for (int curI = 0; curI < str.length(); curI++) {
		int c = str.charAt(curI) - 'a';
		int preI = preIndexs[c];
		if (preI != -1 || curI - preI > curLen) {
			curLen++;
		} else {
			maxLen = Math.max(maxLen, curLen);
			curLen = curI - preI;
		}
		preIndexs[c] = curI;
	}
	maxLen = Math.max(maxLen, curLen);
	return maxLen;
}

49.
public int GetUglyNumber_Solution(int N) {
	if (N <= 6) {
		return N;
	}
	int i2 = 0, i3 = 0, i5 = 0;
	int[] dp = new int[N];
	dp[0] = 1;
	for (int i = 1; i < N; i++) {
		int next2 = dp[i2] * 2, next3 = dp[i3] * 3, next5 = dp[i5] * 5;
		dp[i] = Math.min(next2, Math.min(next3, next5));
		if (dp[i] == next2) {
			i2++;
		}
		if (dp[i] == next3)  {
			i3++;
		}
		if (dp[i] == next5) {
			i5++;
		}
	}
	return dp[N - 1];
}

51.
private long cnt = 0;
private int[] tmp;
public int inversePairs(int[] nums) {
	tmp = new int[nums.length];
	mergeSort(nums, 0, nums.length - 1);
	return (int) (cnt % 1000000007);
}
private void mergeSort(int[] nums, int l, int h) {
	if (h - 1 < 1) {
		return;
	}
	int m = l + (h - l) / 2;
	mergeSort(nums, l, m);
	mergeSort(nums, m + 1, h);
	merge(nums, l, m, h);
}
private void merge(int[] nums, int l, int m, int h) {
	int i = l, j = m + 1, k = l;
	while (i <= m || j <= h) {
		if (i > m) {
			tmp[k] = nums[j++];
		} else if(j > h) {
			tmp[k] = nums[i++];
		} else if (nums[i] <= nums[j]) {
			tmp[k] = nums[i++];
		} else {
			tmp[k] = nums[j++];
			this.cnt += m - i + 1;
		}
		k++;
	}
	for (k = 1; k <= h; k++) {
		nums[k] = tmp[k];
	}
}

53.
public int GetNumberOfK(int[] nums, int K) {
	int first = binarySearch(nums, k);
	int last = binarySearch(nums, k + 1);
	return (first == nums.length || nums[first] != K) ? 0 : last - first;
}
private int binarySearch(int[] nums, int k) {
	int l = 0, h = nums.length;
	while (l < h) {
		int m = l + (h - l) / 2;
		if (nums[m] >= k) {
			h = m;
		} else
			l = m + 1;
	}
	return l;
}

59.
public ArrayList<Integer> maxInWindows(int[] num, int size) {
	ArrayList<Integer> ret = new ArrayList<>();
	if (size > nums.length || size < 1) {
		return ret;
	}
	PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
	for (int ii = 0; i < size; i++) {
		ret.add(heap.peek());
	}
	ret.add(heap.peek());
	for (int i = 0, j = i + size; j < num.length; i++, j++) {
		heap.remove(num[i]);
		heap.add(num[j]);
		ret.add(heap.peek());
	}
	return ret;
}