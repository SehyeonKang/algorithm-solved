import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	static class SegmentTree {
		int[] tree;
		int treeSize;
		
		public SegmentTree(int arrSize) {
			int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
			int treeSize = (int) Math.pow(2, h + 1);
			tree = new int[treeSize];
			Arrays.fill(tree, Integer.MAX_VALUE);
		}
		
		public int init(int[] arr, int node, int start, int end) {
			// 배열의 시작과 끝이 같다면 리프노드이므로 원소 배열 값 그대로 저장
			if (start == end) {
				return tree[node] = arr[start];
			}
			
			// 리프노드가 아니라면 자식노드의 최솟값을 저장
			return tree[node] = Math.min(init(arr, node * 2, start, (start + end) / 2),
					init(arr, node * 2 + 1, (start + end) / 2 + 1, end));
		}
		
		public int update(int node, int start, int end, int idx, int diff) {
			// 변경할 idx 값이 범위 바깥이면 변경 불필요
			if (idx < start || end < idx) {
				return tree[node];
			}
			
			// 배열의 시작과 끝이 같다면 리프노드이므로 변경값 그대로 저장
			if (start == end) {
				return tree[node] = diff;
			}
			
			// 리프노드가 아니라면 아래 자식들을 확인
			return tree[node] = Math.min(update(node * 2, start, (start + end) / 2, idx, diff),
					update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff));
		}
		
		public int min(int node, int start, int end, int left, int right) {
			// 범위를 벗어나게 되는 경우 최솟값을 구할 필요 없음
			if (left > end || right < start) {
				return Integer.MAX_VALUE;
			}
			
			// 범위 내에 완전히 포함될 경우 자식노드로 더 내려가지않고 리턴
			if (left <= start && end <= right) {
				return tree[node];
			}
			
			// 그 외의 경우 자식노드를 탐색
			return Math.min(min(node * 2, start, (start + end) / 2, left, right),
					min(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		SegmentTree tree = new SegmentTree(N);
		tree.init(arr, 1, 1, N);
		
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (flag == 1) {
				tree.update(1, 1, N, a, b);
				arr[a] = b;
			} else {
				sb.append(tree.min(1, 1, N, a, b) + "\n");
			}
		}
		
		System.out.println(sb);
	}
	
	
}
