package part1.queues.interview;

public class StackWithMax extends DefaultStack<Integer>{

    private DefaultStack<Integer> minStack;

    private DefaultStack<Integer> maxStack;

    public StackWithMax(int intialCapacity) {
        super(intialCapacity);
        minStack = new DefaultStack<>();
        maxStack = new DefaultStack<>();
    }

    @Override
    public void push(Integer element) {
        // Current element is lesser or equal than min() value, Push the current element in min stack also.
        if (!minStack.empty()) {
            if (min() >= element) {
                minStack.push(element);
            }
        } else {
            minStack.push(element);
        }
        // Current element is greater or equal than max() value, Push the current element in max stack also.
        if (!maxStack.empty()) {
            if (max() <= element) {
                maxStack.push(element);
            }
        } else {
            maxStack.push(element);
        }
        super.push(element);
    }

    @Override
    public Integer pop() {
        Integer curr = super.pop();
        if (curr != null) {
            if (min() == curr) {
                minStack.pop();
            }

            if (max() == curr) {
                maxStack.pop();
            }
        }
        return curr;
    }

    public int min() {
        return minStack.peek();
    }

    public int max() {
        return maxStack.peek();
    }

}


