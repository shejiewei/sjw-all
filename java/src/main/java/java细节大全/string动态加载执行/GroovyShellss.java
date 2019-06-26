package java细节大全.string动态加载执行;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.io.Serializable;

/**
 * Created by shejiewei on 2019/6/25.
 */
public class GroovyShellss extends GroovyShell implements Serializable {

    public GroovyShellss() {
    }

    public GroovyShellss(Binding binding) {
        super(binding);
    }

    public GroovyShellss(ClassLoader parent, CompilerConfiguration config) {
        super(parent, config);
    }

    public GroovyShellss(CompilerConfiguration config) {
        super(config);
    }

    public GroovyShellss(Binding binding, CompilerConfiguration config) {
        super(binding, config);
    }

    public GroovyShellss(ClassLoader parent, Binding binding) {
        super(parent, binding);
    }

    public GroovyShellss(ClassLoader parent) {
        super(parent);
    }

    public GroovyShellss(ClassLoader parent, Binding binding, CompilerConfiguration config) {
        super(parent, binding, config);
    }

    public GroovyShellss(GroovyShell shell) {
        super(shell);
    }
}
