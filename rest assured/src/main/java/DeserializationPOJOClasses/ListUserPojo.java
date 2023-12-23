package DeserializationPOJOClasses;

import java.util.List;

public class ListUserPojo
{
  private int page;
  private int per_page;
  private int total;
  private int total_pages;

  //because to give nested data inside the data field we are creating seperate class called Datapojo and calling in form of list
  private List<Datapojo> data;

  //Here adpojo is seperate class not nested like sibling so calling with classname
  private Supportpojo support;


  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPer_page() {
    return per_page;
  }

  public void setPer_page(int per_page) {
    this.per_page = per_page;
  }

   public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getTotal_pages() {
    return total_pages;
  }

  public void setTotal_pages(int total_pages) {
    this.total_pages = total_pages;
  }

  public List<Datapojo> getData() {
    return data;
  }

  public void setData(List<Datapojo> data) {
    this.data = data;
  }

  public Supportpojo getSupport() {
    return support;
  }

  public void setSupport(Supportpojo support) {
    this.support = support;
  }

  @Override
  public String toString() {
    return "ListUserPojo{" +
            "page=" + page +
            ", per_page=" + per_page +
            ", total=" + total +
            ", total_pages=" + total_pages +
            ", data=" + data +
            ", support=" + support +
            '}';
  }
}
