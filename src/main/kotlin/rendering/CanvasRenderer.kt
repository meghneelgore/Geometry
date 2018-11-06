package rendering

import java.awt.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

class CanvasRenderer(val canvas: Canvas) {

    private val mainFrame: Frame = Frame("Java AWT Examples")
    private val headerLabel: Label = Label()
    private val statusLabel: Label = Label()
    private val controlPanel: Panel = Panel()

    init {
        prepareGUI()
    }

    private fun prepareGUI() {
        mainFrame.setSize(600, 600)
        mainFrame.layout = GridLayout(1, 1)
        mainFrame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(windowEvent: WindowEvent?) {
                System.exit(0)
            }
        })
        headerLabel.alignment = Label.CENTER
        statusLabel
        statusLabel.alignment = Label.CENTER
        statusLabel.setSize(500, 500)

        controlPanel.layout = FlowLayout()
        controlPanel.add(canvas)

        mainFrame.add(controlPanel)
        mainFrame.isVisible = true
    }
}