package java细节大全.泛型存类;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractProvider
{
    protected String name ;
    protected Map<String, Class<?>> providedTypes = new HashMap<>() ;
    protected Set<String> enabledSchemes=new HashSet<>();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public Map<String, Class<?>> getProvidedTypes()
    {
        return providedTypes;
    }


    public Set<String> getEnabledSchemes()
    {
        return enabledSchemes;
    }

    protected void enableAllSchemes()
    {
        enabledSchemes.addAll( providedTypes.keySet() ) ;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
