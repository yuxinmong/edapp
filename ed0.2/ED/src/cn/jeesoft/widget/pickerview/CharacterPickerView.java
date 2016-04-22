package cn.jeesoft.widget.pickerview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

import com.ed.v1.R;

/**
 * 鏂囨湰閫夋嫨鍣�
 *
 * @version 0.1 king 2015-11
 */
public class CharacterPickerView extends FrameLayout {

	public static interface OnOptionChangedListener {
		public void onOptionChanged(CharacterPickerView view, int option1,
				int option2, int option3);
	}

	private MWheelOptions wheelOptions;

	public CharacterPickerView(Context context) {
		super(context);
		init(context);
	}

	public CharacterPickerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CharacterPickerView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context) {
		View.inflate(context, R.layout.j_picker_items, this);
		wheelOptions = new MWheelOptions(this);
	}

	public void setPicker(List<String> optionsItems) {
		wheelOptions.setPicker(optionsItems, null, null);
	}

	public void setPicker(List<String> options1Items,
			List<List<String>> options2Items) {
		wheelOptions.setPicker(options1Items, options2Items, null);
	}

	public void setPicker(List<String> options1Items,
			List<List<String>> options2Items,
			List<List<List<String>>> options3Items) {
		wheelOptions.setPicker(options1Items, options2Items, options3Items);
	}

	public void setTimePicker(List<String> options1Items,
			List<String> options2Items, List<String> options3Items) {
		wheelOptions.setTimePicker(options1Items, options2Items, options3Items);
	}

	/**
	 * 璁剧疆閫変腑鐨刬tem浣嶇疆
	 *
	 * @param option1
	 */
	public void setSelectOptions(int option1) {
		wheelOptions.setCurrentItems(option1, 0, 0);
	}

	/**
	 * 璁剧疆閫変腑鐨刬tem浣嶇疆
	 *
	 * @param option1
	 * @param option2
	 */
	public void setSelectOptions(int option1, int option2) {
		wheelOptions.setCurrentItems(option1, option2, 0);
	}

	/**
	 * 璁剧疆閫変腑鐨刬tem浣嶇疆
	 *
	 * @param option1
	 * @param option2
	 * @param option3
	 */
	public void setSelectOptions(int option1, int option2, int option3) {
		wheelOptions.setCurrentItems(option1, option2, option3);
	}

	/**
	 * 璁剧疆鏄惁寰幆婊氬姩
	 *
	 * @param cyclic
	 */
	public void setCyclic(boolean cyclic) {
		wheelOptions.setCyclic(cyclic);
	}

	public void setCurrentItems(int option1, int option2, int option3) {
		wheelOptions.setCurrentItems(option1, option2, option3);
	}

	/**
	 * 杩斿洖褰撳墠閫変腑鐨勭粨鏋滃搴旂殑浣嶇疆鏁扮粍 鍥犱负鏀寔涓夌骇鑱斿姩鏁堟灉锛屽垎涓変釜绾у埆绱㈠紩锛�0锛�1锛�2
	 *
	 * @return
	 */
	public int[] getCurrentItems() {
		return wheelOptions.getCurrentItems();
	}

	public void setOnOptionChangedListener(OnOptionChangedListener listener) {
		this.wheelOptions.setOnOptionChangedListener(listener);
	}

}
