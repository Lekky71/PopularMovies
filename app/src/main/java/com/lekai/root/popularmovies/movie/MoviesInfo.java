package  com.lekai.root.popularmovies.movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MoviesInfo implements Serializable, Parcelable
{

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Movie> results = null;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    public final static Parcelable.Creator<MoviesInfo> CREATOR = new Creator<MoviesInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MoviesInfo createFromParcel(Parcel in) {
            MoviesInfo instance = new MoviesInfo();
            instance.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.results, (com.lekai.root.popularmovies.movie.Movie.class.getClassLoader()));
            instance.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
            return instance;
        }

        public MoviesInfo[] newArray(int size) {
            return (new MoviesInfo[size]);
        }

    }
            ;
    private final static long serialVersionUID = -3294705155279297798L;

    /**
     * No args constructor for use in serialization
     *
     */
    public MoviesInfo() {
    }

    /**
     *
     * @param results
     * @param totalResults
     * @param page
     * @param totalPages
     */
    public MoviesInfo(Integer page, List<Movie> results, Integer totalResults, Integer totalPages) {
        super();
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeList(results);
        dest.writeValue(totalResults);
        dest.writeValue(totalPages);
    }

    public int describeContents() {
        return 0;
    }

}