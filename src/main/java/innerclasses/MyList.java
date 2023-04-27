package innerclasses;

import java.util.Iterator;


public class MyList<E> {
  private E[] data = (E[])new Object[10];
  private int count = 0;

  // default constructor good enough

  public void add(E e) {
    data[count++] = e; // IGNORING OVERFLOW!!!
  }

  public E get(int idx) {
    return data[idx]; // IGNORING subscript out of range
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("MyList[");
    for (int idx = 0; idx < count; idx++) {
      sb.append(data[idx] + ", ");
    }
    sb.setLength(sb.length() - 2);
    sb.append("]");
    return sb.toString();
  }

//  static class MyListIterator<E> implements Iterator<E> {
  class MyListIterator implements Iterator<E> {
//    private MyList<E> self; <-- became "MyList.this"
    private int progress = 0;

//    public MyListIterator(MyList<E> self) {
//    public MyListIterator(MyList<E> MyList.this) {
    public MyListIterator() {
//      this.self = self;
//      this.self = MyList.this;
    }

    @Override
    public boolean hasNext() {
//      return progress < self.count;
//      return progress < MyList.this.count;
      return progress < count;
    }

    @Override
    public E next() {
//      return self.data[progress++];
//      return MyList.this.data[progress++];
      return data[progress++];
    }
  }

}


class TryMyList {
  public static void main(String[] args) {
    MyList<String> mls = new MyList<>();
    mls.add("Fred");
    mls.add("Jim");
    mls.add("Sheila");
    System.out.println(mls);

//    Iterator<String> myIter = new MyList.MyListIterator(mls);
    Iterator<String> myIter = mls.new MyListIterator();
    while (myIter.hasNext()) {
      System.out.println("> " + myIter.next());
    }
  }
}